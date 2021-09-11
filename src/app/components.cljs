(ns app.components
  (:require
   ["@fortawesome/free-solid-svg-icons" :refer [faEllipsisH]]
   ["@fortawesome/react-fontawesome" :refer [FontAwesomeIcon]]
   ["fitvids" :as fitvids]
   ["highlight.js" :as hljs]
   [app.data :as data]
   [app.nightwind :refer [dark-light-button]]
   [headlessui-reagent.core :as hui]
   [reagent.core :as reagent]
   [reagent.dom :as r.dom]
   [reitit.frontend.easy :as rfe]))

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
                   [:nav {:class (conj navbar-styles "-sm:hidden" "grid" "grid-flow-col" "grid-cols-2")}
                    [:div {:class ["gap-2" "justify-start" "inline-flex"]}
                     (desktop-nav-link (first nav-data) link-styles)]
                    [:div {:class ["gap-2" "justify-end" "inline-flex"]}
                     (nav-links desktop-nav-link (rest nav-data))
                     [dark-light-button link-styles]]])
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

(defn display-date
  [date-string]
  (let [date (js/Date. date-string)]
    (. date (toLocaleDateString))))

(defn this-year
  []
  (let [now (js/Date.)]
    (.getFullYear now)))

(defn blog-post-preview
  [blog-post]
  [:div {:class ["border-2" "rounded" "border-gray-500" "m-2" "pt-2" "pb-4" "px-4" "bg-gray-200"]}
   [:a {:href (rfe/href :app.router/post {:id (-> blog-post :meta :id)})}
    [:h2 {:class ["text-2xl"]} (-> blog-post :meta :title)]
    [:p {:class ["text-xs py-0.5 text-gray-500"]}
     (display-date (-> blog-post :meta :date))]
    [:p {:class ["text-sm" "overflow-ellipsis" "line-clamp-5" "text-gray-600"]}
     (-> blog-post :meta :subtitle)]]])

(defn- highlight-code [node]
  (when (.querySelector (r.dom/dom-node node) "pre code")
    (-> (r.dom/dom-node node)
        (.querySelectorAll "pre code")
        array-seq
        first
        hljs/highlightElement)
    (doseq [code-block (array-seq (.querySelectorAll (r.dom/dom-node node) "pre code"))]
      (hljs/highlightElement code-block))))

(defn blog-post-main-view
  [blog-post]
  (reagent/create-class
   {:component-did-mount
    (fn [node]
      (highlight-code node)
      (fitvids))
    :reagent-render
    (fn [blog-post]
      [:div {:class ["m-2" "mt-6" "pt-2" "z-10" "pb-4" "px-2" "sm:px-8" "overflow-auto grid"]}
       [:h1 {:class ["font-bold text-4xl"]} (-> blog-post :meta :title)]
       [:p {:class ["pb-4" "text-gray-500"]}
        (display-date (-> blog-post :meta :date))]
       [:article {:class ["prose" "prose-sm" "sm:prose" "lg:prose-lg" "justify-self-center"]
                  :dangerouslySetInnerHTML
                  {:__html (:content blog-post)}}]])}))

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
