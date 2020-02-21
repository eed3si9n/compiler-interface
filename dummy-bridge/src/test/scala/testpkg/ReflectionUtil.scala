package testpkg

import java.nio.file.Paths
import java.net.URLClassLoader
import java.lang.reflect.InvocationTargetException

object ReflectionUtil {

  def bridgeInstance(bridgeClassName: String): AnyRef = {
    val bridgeClass = getBridgeClass(bridgeClassName)
    bridgeClass.getDeclaredConstructor().newInstance().asInstanceOf[AnyRef]
  }

  def getBridgeClass(name: String) =
    Class.forName(name, true, loader)

  def siJars =
    sys
      .props("test.sijars")
      .split(sys.props("path.separator"))
      .toList
      .map(Paths.get(_))

  def scalaLibraryJar = siJars.find(_.toString.contains("scala-library")).get

  lazy val scalaInstaceLoader =
    new URLClassLoader(siJars.map(_.toUri.toURL).toArray)

  lazy val loader = {
    val bridgeJar = Paths.get(sys.props("test.bridgejar"))
    new URLClassLoader(
      Array(bridgeJar.toUri.toURL),
      createDualLoader(scalaInstaceLoader, getClass.getClassLoader)
    )
  }

  def createDualLoader(
      scalaLoader: ClassLoader,
      sbtLoader: ClassLoader
  ): ClassLoader = {
    val intfFilter = (name: String) => name.startsWith("scala.tools.sci.")
    val notIntfFilter = (name: String) => !intfFilter(name)
    new DualLoader(
      scalaLoader,
      notIntfFilter,
      _ => true,
      sbtLoader,
      intfFilter,
      _ => false
    )
  }
}
