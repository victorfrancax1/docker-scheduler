(defproject docker-scheduler "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/data.json "0.2.6"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [amazonica "0.3.145"]
                 [metosin/compojure-api "1.1.11"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler docker-scheduler.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
