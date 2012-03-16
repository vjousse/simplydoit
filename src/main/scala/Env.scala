package doit

import com.typesafe.config._

trait Env {

  val config: Config

  lazy val tickFrequency: Int = config getInt "simplydoit.tickFrequency"
  lazy val sessionSeconds: Int = config getInt "simplydoit.sessionSeconds"
}

object Env extends EnvBuilder {

  def apply(overrides: String = "") = {
    val c = makeConfig(overrides)

    //Early failure check
    c getInt "simplydoit.tickFrequency"

    new Env { val config = c }
  }
}

trait EnvBuilder {

  import java.io.File

  def makeConfig(sources: String*) = sources.foldLeft(ConfigFactory.defaultOverrides) {
    case (config, source) if source isEmpty ⇒ config
    case (config, source) if source contains '=' ⇒
      config.withFallback(ConfigFactory parseString source)
    case (config, source) ⇒ {
      config.withFallback(ConfigFactory parseFile (new File(source)))
    }
  }
}
