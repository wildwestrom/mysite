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
   [:div.min-h-screen.flex.flex-col
    [nav-bar (mapv (fn [eachmap] (assoc eachmap :href (rfe/href (:href eachmap))))
                   [{:title "Home" :href :app.data.router/home}
                    {:title "Blog" :href :app.data.router/blog}
                    {:title "Projects" :href :app.data.router/projects}])]
    [:div.page-container
     (if @global/current-page
       (let [view (:view (:data @global/current-page))]
         [view @global/current-page])
       [not-found-page])]]
   [common/license]])
