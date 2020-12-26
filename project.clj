(defproject org-ckons-cljs.notifications "0.1.0-SNAPSHOT"
  :description "A small library which manages the message and errormsg
  browser notifications."
  :url "FIXME"
  :license "public domain"
  :dependencies [[org.clojure/clojure "LATEST"]
                 [org.clojure/clojurescript "LATEST"]
                 [prismatic/dommy "LATEST"]
                 [hiccups "LATEST"]]
  :plugins [[lein-cljsbuild "LATEST"]]
  :clean-targets ^{:protect false} [:target-path "out" "resources/public/cljs"])
