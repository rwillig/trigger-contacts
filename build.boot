#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.5.0"

(set-env!
  :project      'contacts
  :version      "0.0.1"
  :dependencies '[[tailrecursion/boot.task   "2.2.2"]
                  [tailrecursion/boot.notify "2.0.2"]
                  [tailrecursion/hoplon      "5.10.14"]
                  [io.hoplon/twitter.bootstrap "0.1.0"]
                  [tailrecursion/boot.ring   "0.2.1"]]
  :out-path     "resources/public"
  :src-paths    #{"source"})

;; Static resources (css, images, etc.):
(add-sync! (get-env :out-path) #{"assets"})

(require '[tailrecursion.hoplon.boot :refer :all]
         '[tailrecursion.boot.task.notify :refer [hear]]
         '[tailrecursion.boot.task.ring   :refer [dev-server]])

(deftask development
  "Build layers for development."
  []
  (comp (watch) (hear) (hoplon {:source-map true :prerender false :pretty-print true}) (dev-server)))

(deftask production
  "Build layers for production."
  []
  (hoplon {:optimizations :advanced :source-map true :pretty-print true}))
