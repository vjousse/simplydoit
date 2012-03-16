package doit

object Cli {

  def main(args: Array[String]): Unit = sys exit {
    println(args.toList match {
      case config :: command :: args ⇒ {
        val env = Env(config)
        EnvCli(env)(command, args)
      }
      case _ ⇒ "Usage: run /path/to/simplydoit.conf command args"
    })
    0
  }

  case class EnvCli(env: Env) {

    def apply(command: String, args: List[String]) = command match {
      case "start" => startSession()
      case command  ⇒ "Unknown command " + command
    }

    def startSession(): String = {
      val doit = new Doit(env)
      doit.startSession
      "Session started"
    }

  }

}
