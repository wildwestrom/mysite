(ns app.components.projects
  (:require [app.components.common :as common]
            [app.portfolio :refer [portfolio]]))

(defn- project-entry [{:keys [name desc links]}]
  [:div.mt-4
   [:h3 {:class ["text-2xl"]} name]
   [:ul
    (for [{:keys [title href icon]} links]
      ^{:key title}
      [:li
       [common/icon-link title icon nil :href href :styles "my-2"]])]
   desc])

(defn- group-of-projects
  [{:keys [group-name projects]}]
  [:<>
   [:h2 {:class ["mt-4" "text-3xl" "font-bold"]} group-name]
   (for [project projects]
     ^{:key (:name project)}
     [project-entry project])])

(defn projects-page
  []
  [common/page-container
   (for [project-group portfolio]
     ^{:key (:group-name project-group)}
     [group-of-projects project-group])])
