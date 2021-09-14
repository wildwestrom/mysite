(ns app.components.projects
  (:require [app.components.common :as common]
            [app.portfolio :refer [portfolio]]))

(defn- project-entry [{:keys [name desc links]}]
  [:div.mt-4
   [:h2 {:class ["text-2xl"]} name]
   [:ul
    (for [{:keys [title href]} links]
      ^{:key title}
      [:li
       [common/generic-link href title]])]
   desc])

(defn- group-of-projects
  [{:keys [group-name projects]}]
  [:<>
   [:h1.mt-4 {:class ["text-3xl" "font-bold"]} group-name]
   (for [project projects]
     ^{:key (:name project)}
     [project-entry project])])

(defn projects-page
  []
  [:div {:class ["justify-self-center" "self-start" "sm:mt-2" "p-4" "max-w-prose"]}
   (for [project-group portfolio]
     ^{:key (:group-name project-group)}
     [group-of-projects project-group])])
