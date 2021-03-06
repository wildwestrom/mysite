(ns app.components.common
  (:require ["@fortawesome/react-fontawesome" :refer [FontAwesomeIcon]]
            [app.data.global :as data]
            [headlessui-reagent.core :as hui]
            [reagent.core :as reagent]))

(defn display-date
  [date-string]
  (let [date (js/Date. date-string)]
    (.toLocaleDateString date)))

(defn this-year
  []
  (let [now (js/Date.)]
    (.getFullYear now)))

(defn generic-link
  [link text & {:keys [alt-target]}]
  [:a {:href   link
       :target (if alt-target alt-target "_blank")
       :class  ["text-blue-500" "hover:text-blue-600"]} text])

(defn license
  []
  [:footer {:class ["p-2" "text-xs" "text-center" "self-center"]}
   [:p "This site is "
    (generic-link
      "https://www.gnu.org/licenses/agpl-3.0.html" "free software")
    ". " [:br.xs:hidden.block] "Get the source code " (generic-link "https://github.com/wildwestrom/mysite" "here.")
    [:br]
    "Copyright © "
    (this-year) " "
    (:author data/global-config) " "
    [:br.xs:hidden.block]
    (generic-link (str "mailto:" (:email data/global-config))
                  (:email data/global-config))]])

(defn icon-link [text icon label & {:keys [copyable href styles] :or {styles "my-4"}}]
  (reagent/with-let [showing? (reagent/atom false)]
    [:li {:class (let [base-styles ["text-blue-500" "hover:text-blue-600"]]
                   (cond
                     (string? styles) (conj base-styles styles)
                     (coll? styles)   (concat base-styles styles)
                     :else            base-styles))}
     (when copyable
       [hui/transition
        {:id         "text-copy-indicator"
         :show       @showing?
         :enter      "transition-opacity duration-75"
         :enter-from "opacity-0"
         :enter-to   "opacity-100"
         :leave      "transition-opacity duration-300"
         :leave-from "opacity-100"
         :leave-to   "opacity-0"
         :class      ["border-2" "rounded-lg" "p-1" "absolute" "text-black" "bg-blue-50" "transform" "-translate-y-10" "z-50"]}
        "Copied to Clipboard!"])
     [:a.cursor-pointer
      (merge
        {:title      (str label (when copyable " (click to copy)"))
         :aria-label label
         :class      ["text-blue-500" "hover:text-blue-600"]}
        (when href {:href   href
                    :target "_blank"})
        (when copyable
          {:on-click (fn []
                       (letfn [(toggle [] (swap! showing? not))]
                         (js/navigator.clipboard.writeText text)
                         (toggle)
                         (js/setTimeout toggle 1500)))}))
      [:> FontAwesomeIcon {:icon  icon
                           :class "fa-fw mr-2 fill-inherit"}]
      text]]))
