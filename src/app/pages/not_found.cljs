(ns app.pages.not-found
  (:require [app.components.common :as common]))

(defn not-found-page
  []
  [common/page-container
   [:h2.font-mono
    "Error:" [:br] "Page not found."]])
