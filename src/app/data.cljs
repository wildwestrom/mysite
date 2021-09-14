(ns app.data
  (:require [clojure.edn :as edn]
            [reagent.core :as reagent]
            shadow.resource))

(defonce match (reagent/atom nil))

(def global-config
  (edn/read-string (shadow.resource/inline "config/site-data.edn")))
