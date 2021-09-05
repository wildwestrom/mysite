(ns app.nightwind
  (:require ["nightwind/helper" :refer [toggle]]
            [reagent.core :as reagent]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Re-implement the init function from nightwind helper
(defn get-initial-color-mode
  []
  (let [persistedColorPreference (.getItem (.-localStorage js/window) "nightwind-mode")
        hasPersistedPreference   (string? persistedColorPreference)]
    (if hasPersistedPreference
      persistedColorPreference
      (let [mql                     (.matchMedia js/window "(prefers-color-scheme: dark)")
            hasMediaQueryPreference (boolean? (.-matches mql))]
        (when hasMediaQueryPreference (if (.-matches mql) "dark" "light"))))))

(defn init-nightwind []
  (if (= (get-initial-color-mode) "light")
    (.remove (.. js/document -documentElement -classList) "dark")
    (.add (.. js/document -documentElement -classList) "dark")))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn inject-dark-mode
  []
  (.appendChild (.appendChild (.querySelector js/document "head")
                              (.createElement js/document "script"))
                (.createTextNode js/document (init-nightwind))))

(defonce icon
  (reagent/atom (case (get-initial-color-mode)
                  "dark"  "🌚"
                  "light" "🌞")))

(defn change-icon!
  []
  (let [mut (new js/MutationObserver (fn [_ _]
                                       (if (js/document.documentElement.classList.contains "dark")
                                         (reset! icon "🌚")
                                         (reset! icon "🌞"))))]
    (.observe mut js/document.documentElement #js {:attributes true})))


(defn dark-light-button
  []
  [:button {:onClick (comp toggle change-icon!)
            :title "Click to toggle theme."} @icon])
