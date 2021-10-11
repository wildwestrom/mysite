(ns app.pages.projects
  (:require [app.components.common :as common]
            [app.data.projects :refer [portfolio]]))

(defn- project-entry [{:keys [name desc links]}]
  [:div.mt-4
   [:h3 {:class ["text-2xl"]} name]
   [:ul
    (for [{:keys [title href icon]} links]
      ^{:key title}
      [common/icon-link title icon nil :href href :styles "my-2"])]
   (into [] (concat [:p] desc))])

(defn- group-of-projects
  [{:keys [group-name projects]}]
  [:<>
   [:h1 group-name]
   (for [project projects]
     ^{:key (:name project)}
     [project-entry project])])

(defn projects-page
  []
  [:<>
   (for [project-group portfolio]
     ^{:key (:group-name project-group)}
     [group-of-projects project-group])])
