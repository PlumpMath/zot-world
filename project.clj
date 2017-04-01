(defproject the-daily-zot "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/clojurescript "1.9.495"]]
  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-figwheel "0.5.9"]]
  :clean-targets ^{:protect false} ["target"]
  :cljsbuild {
    :builds {
      :dev {:source-paths ["src"]
            :figwheel true
            :compiler {:main the-daily-zot.core
                       :target :nodejs
                       :optimizations :none
                       :output-dir "target"
                       :output-to "target/server.js"
                       :source-map true}}}}
  :profiles {
    :production {
      :cljsbuild {
        :builds {
          :dev {:figwheel false
                :compiler {:source-map false}}}}}}
  :figwheel {})
