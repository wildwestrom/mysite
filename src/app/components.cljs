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

(defn nav-link
  [title route [& classes]]
  (reagent/with-let [rt (rfe/href route)]
    [:a {:class (into [] classes)
         :href rt
         :title title} title]))

(defn mobile-nav-link
  [title route [& classes]]
  (reagent/with-let [rt (rfe/href  route)]
    [hui/menu-item {:class (into [] classes)
                    :as :a
                    :href rt} title]))

(defn navbar
  []
  (reagent/with-let [nav-classes ["font-serif" "italic" "bg-gray-300" "text-lg" "p-2" "z-50"]
                     toggle-button-classes ["px-2" "py-1" "border-1" "bg-gray-200" "rounded"]]
    ;; Desktop Nav
    [:<>
     [:nav {:class (conj nav-classes "-sm:hidden" "grid" "grid-flow-col" "grid-cols-2")}
      [:div {:class ["gap-2" "justify-start" "inline-flex"]}
       [nav-link "Home" :app.router/home toggle-button-classes]]
      [:div {:class ["gap-2" "justify-end" "inline-flex"]}
       [nav-link "Projects" :app.router/projects toggle-button-classes]
       [nav-link "Blog" :app.router/blog toggle-button-classes]
       [nav-link "About" :app.router/about toggle-button-classes]
       [dark-light-button toggle-button-classes]]]
     ;; Mobile Nav
     [hui/menu {:as :nav
                :class-name (conj nav-classes "m-4" "sm:hidden" "fixed" "bottom-0" "rounded-lg" "right-0" "grid" "gap-2")}
      [hui/menu-button {:class-name ["border-1" "bg-gray-200" "rounded"]}
       [:> FontAwesomeIcon {:icon faEllipsisH}]]
      [hui/menu-items {:class ["grid" "gap-2"]}
       [mobile-nav-link "Home" :app.router/home toggle-button-classes]
       [mobile-nav-link "Projects" :app.router/projects toggle-button-classes]
       [mobile-nav-link "Blog" :app.router/blog toggle-button-classes]
       [mobile-nav-link "About" :app.router/about toggle-button-classes]
       [hui/menu-item {:as (fn [] [dark-light-button toggle-button-classes])}]]]]))

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
