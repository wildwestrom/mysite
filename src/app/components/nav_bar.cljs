(ns app.components.nav-bar
  (:require ["@fortawesome/free-solid-svg-icons/faEllipsisH" :refer [faEllipsisH]]
            ["@fortawesome/react-fontawesome" :refer [FontAwesomeIcon]]
            [app.components.nightwind :refer [dark-light-button]]
            [app.data.global :refer [current-page]]
            [headlessui-reagent.core :as hui]
            [reagent.core :as reagent]))

(defn desktop-nav-link
  [{:keys [title href]}]
  ^{:key title}
  [:a {:class "nav-link"
       :href href
       :title title} title])

(defn mobile-nav-link
  [{:keys [title href]}]
  ^{:key title}
  [hui/menu-item {:class "nav-link"
                  :as :a
                  :href href} title])

(defn nav-bar
  [nav-data]
  (reagent/with-let
    [nav-links (fn [link-fn nav-data]
                 [:<>
                  (for [nav nav-data]
                    ^{:key (-> nav :title)}
                    (link-fn nav))])
     desktop-nav (fn [nav-data]
                   [:nav {:class ["nav-bar"
                                  "flex" "flex-row"
                                  "text-center" "justify-between"
                                  "items-center"
                                  "-sm:hidden" "min-w-full"
                                  "px-8" "py-2"]}
                    [:div
                     ;;TODO Come up with something better.
                     [:a.text-3xl {:href "/"}
                      "westrom.xyz"]
                     [:h1.sr-only
                      (str (-> @current-page :data :display))]]
                    [:div {:class ["grid" "grid-flow-col" "mx-4" "gap-4"]}
                     (nav-links desktop-nav-link nav-data)
                     [dark-light-button]]])
     mobile-nav (fn [nav-data]
                  [hui/menu {:as :nav
                             :class-name ["nav-bar"
                                          "m-4" "p-2" "sm:hidden" "fixed"
                                          "bottom-0" "rounded-lg"
                                          "right-0" "text-center"]}
                   [hui/menu-button {:class-name ["border-1" "bg-gray-200" "rounded"]}
                    [:> FontAwesomeIcon {:icon faEllipsisH}]]
                   [hui/menu-items {:class ["grid" "gap-2"]}
                    (nav-links mobile-nav-link nav-data)
                    [hui/menu-item {:as (fn [] [dark-light-button])}]]])]
    [:<>
     (desktop-nav nav-data)
     (mobile-nav nav-data)]))
