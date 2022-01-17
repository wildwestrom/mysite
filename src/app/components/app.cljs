(ns app.components.app
  (:require [app.components.common :as common]
            [app.components.nav-bar :refer [nav-bar]]
            [app.pages.not-found :refer [not-found-page]]
            [app.data.global :as global]
            [reitit.frontend.easy :as rfe]))

;; This is the main entry point into the site.

(defn app
  []
  [:<>
   [nav-bar (mapv (fn [eachmap] (assoc eachmap :href (rfe/href (:href eachmap))))
                  global/nav-links)]
   [:main.page-container
    (if @global/current-page
      (let [view (:view (:data @global/current-page))]
        [view @global/current-page])
      [not-found-page])]
   [common/license]])
