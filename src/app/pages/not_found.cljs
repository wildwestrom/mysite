(ns app.pages.not-found
  (:require [app.components.common :as common]))

(defn not-found-page
  []
  [common/page-container
   [:h2 {:class ["text-5xl" "p-4" "font-mono"]}
   "Error:" [:br] "Page not found."]])
