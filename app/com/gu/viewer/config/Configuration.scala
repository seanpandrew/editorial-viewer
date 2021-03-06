package com.gu.viewer.config

import play.api.Play.current
import com.gu.viewer.aws.AWS

object Configuration {

  // parsed config from application.conf
  private val config = play.api.Play.configuration

  private def getConfigString(key: String) =
    config.getString(key).getOrElse {
      sys.error(s"Config key required: $key")
    }

  lazy val app: String = AWS.readTag("App").getOrElse("viewer")

  lazy val stage: String = AWS.readTag("Stage").getOrElse("DEV")

  lazy val stack: String = AWS.readTag("Stack").getOrElse("flexible")

  val previewHost = getConfigString(s"previewHost.$stage")
  val liveHost = getConfigString(s"liveHost.$stage")
  val previewHostForceHTTP = config.getBoolean(s"previewHostForceHTTP.$stage").getOrElse(false)
  val mixpanel = getConfigString(s"mixpanel.$stage")
  val composerReturn = getConfigString(s"composerReturnUri.$stage")

  def pandaDomain = {
    if (stage == "PROD") {
      "gutools.co.uk"
    } else if (stage == "CODE") {
      "code.dev-gutools.co.uk"
    } else {
      "local.dev-gutools.co.uk"
    }
  }

  def pandaAuthCallback = {
    if (stage == "PROD") {
      "https://viewer.gutools.co.uk/oauthCallback"
    } else if (stage == "CODE") {
      "https://viewer.code.dev-gutools.co.uk/oauthCallback"
    } else {
      "https://viewer.local.dev-gutools.co.uk/oauthCallback"
    }
  }
}
