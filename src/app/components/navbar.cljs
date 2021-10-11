(ns app.components.navbar
  (:require ["@fortawesome/free-solid-svg-icons/faEllipsisH" :refer [faEllipsisH]]
            ["@fortawesome/react-fontawesome" :refer [FontAwesomeIcon]]
            [headlessui-reagent.core :as hui]
            [reagent.core :as reagent]
            [app.components.nightwind :refer [dark-light-button]]))

(defn desktop-nav-link
  [{:keys [title href]} [& classes]]
  ^{:key title}
  [:a {:class (into [] classes)
       :href href
       :title title} title])

(defn mobile-nav-link
  [{:keys [title href]} [& classes]]
  ^{:key title}
  [hui/menu-item {:class (into [] classes)
                  :as :a
                  :href href} title])

(defn navbar
  [nav-data]
  (reagent/with-let
    [navbar-styles ["font-serif" "italic" "bg-gray-300" "text-lg" "p-2" "z-50"]
     link-styles ["px-2" "py-1" "border-1" "bg-gray-200" "rounded"]
     nav-links (fn [link-fn nav-data]
                 [:<>
                  (for [nav nav-data]
                    ^{:key (-> nav :title)}
                    (link-fn nav link-styles))])
     desktop-nav (fn [nav-data]
                   [:nav {:class (conj navbar-styles "-sm:hidden" "gap-2" "justify-end" "inline-flex")}
                    (nav-links desktop-nav-link nav-data)
                    [dark-light-button link-styles]])
     mobile-nav (fn [nav-data]
                  [hui/menu {:as :nav
                             :class-name (conj navbar-styles "m-4" "sm:hidden" "fixed" "bottom-0" "rounded-lg" "right-0" "text-center")}
                   [hui/menu-button {:class-name ["border-1" "bg-gray-200" "rounded"]}
                    [:> FontAwesomeIcon {:icon faEllipsisH}]]
                   [hui/menu-items {:class ["grid" "gap-2"]}
                    (nav-links mobile-nav-link nav-data)
                    [hui/menu-item {:as (fn [] [dark-light-button link-styles])}]]])]
    [:<>
     (desktop-nav nav-data)
     (mobile-nav nav-data)]))
