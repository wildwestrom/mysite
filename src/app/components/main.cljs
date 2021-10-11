(ns app.components.main
  (:require [app.components.common :as common]
            [app.components.navbar :refer [navbar]]
            [app.pages.not-found :refer [not-found-page]]
            [app.data.global :as data]
            [reitit.frontend.easy :as rfe]))

(defn app
  []
  [:div {:class ["text-gray-700" "bg-gray-50"
                 "subpixel-antialiased" "min-h-screen"]}
   [:div.min-h-screen.flex.flex-col
    [navbar (mapv (fn [eachmap] (assoc eachmap :href (rfe/href (:href eachmap))))
                  [{:title "About" :href :app.router/about}
                   {:title "Blog" :href :app.router/blog}
                   {:title "Projects" :href :app.router/projects}])]
    [:div.grid.flex-grow
     (if @data/match
       (let [view (:view (:data @data/match))]
         [view @data/match])
       [not-found-page])]]
   [common/license]])
