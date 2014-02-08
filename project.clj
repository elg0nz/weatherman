(defproject weatherman "0.1.0-SNAPSHOT"
  :description "Weather reports to redis"
  :main weatherman.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :dependencies [
                 [org.clojure/clojure "1.5.1"],
                 [org.clojure/data.json "0.2.3"],
                 [clj-http "0.7.7"]
                 ])
