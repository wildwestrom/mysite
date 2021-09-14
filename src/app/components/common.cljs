(ns app.components.common
  (:require ["@fortawesome/react-fontawesome" :refer [FontAwesomeIcon]]
            [app.data :as data]
            [headlessui-reagent.core :as hui]
            [reagent.dom :as r.dom]
            [reagent.core :as reagent]
            [reitit.frontend.easy :as rfe]))

(defn display-date
  [date-string]
  (let [date (js/Date. date-string)]
    (. date (toLocaleDateString))))

(defn this-year
  []
  (let [now (js/Date.)]
    (.getFullYear now)))

(defn generic-link
  [link text & {:keys [mail]}]
  [:a {:href   (if mail
                 (str "mailto: " link)
                 link)
       :target "_blank"
       :class  ["text-blue-500" "hover:text-blue-600"]} text])

(defn license
  []
  [:footer {:class ["p-2" "text-xs" "text-center" "self-center"]}
   [:p (generic-link
        "https://github.com/wildwestrom/mysite" "This webpage")
    " is licensed"
    [:br.xs:hidden.block]
    " under the "
    (generic-link
     "https://www.gnu.org/licenses/agpl-3.0.html" "GNU AGPL License")
    "." [:br]
    "Copyright © "
    (this-year) " "
    (:author data/global-config) " "
    [:br.xs:hidden.block]
    (generic-link (:email data/global-config)
                  (:email data/global-config) :mail true)]])

(defn icon-link [text icon label & {:keys [copyable href]}]
  (reagent/with-let [showing? (reagent/atom false)]
    [:li {:class ["py-2" "text-blue-600" "hover:text-blue-700"]}
     (when copyable
       [hui/transition
        {:id "text-copy-indicator"
         :show @showing?
         :enter "transition-opacity duration-75"
         :enter-from "opacity-0"
         :enter-to "opacity-100"
         :leave "transition-opacity duration-300"
         :leave-from "opacity-100"
         :leave-to "opacity-0"
         :class ["border-2" "rounded-lg" "p-1" "absolute" "text-black" "bg-blue-50" "transform" "-translate-y-10"]}
        "Copied to Clipboard!"])
     [:a.cursor-pointer
      (merge
       (when href {:href href})
       (when label
         {:title (str label (when copyable " (click to copy)"))
          :aria-label label})
       (when copyable
         {:on-click  (fn []
                       (letfn [(toggle [] (swap! showing? not))]
                         (js/navigator.clipboard.writeText text)
                         (toggle)
                         (js/setTimeout toggle 500)))}))
      [:> FontAwesomeIcon {:icon icon
                           :class "fa-fw mr-2"}]
      text]]))
