(ns app.components
  (:require ["@fortawesome/free-brands-svg-icons" :as fab]
            ["@fortawesome/free-solid-svg-icons" :as fas]
            ["@fortawesome/react-fontawesome" :refer [FontAwesomeIcon]]
            ["@headlessui/react" :as headlessui]
            ["fitvids" :as fitvids]
            ["highlight.js" :as hljs]
            [app.data :as data]
            [app.nightwind :refer [dark-light-button]]
            [reagent.core :as reagent]
            [reagent.dom :as r.dom]
            [reitit.frontend.easy :as rfe]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialization
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defonce blog-posts (reagent/atom nil))

(data/store-posts-data blog-posts)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Components
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn nav-link
  [title route & pos]
  (reagent/with-let [rt (rfe/href route)]
    [:a {:class ["px-2" "py-1" "border-1"
                 "bg-gray-200" "rounded"]
         :href rt
         :title title} title]))

(def navs
  [["Home" :app.router/home]
   ["Blog" :app.router/blog]
   ["About" :app.router/about]])

(defn navbar
  []
  (reagent/with-let [nav-classes ["font-serif" "italic" "bg-gray-300" "text-lg" "p-2"]
                     toggle-button-classes ["px-2" "py-1" "border-1" "bg-gray-200" "rounded"]]
    ;; Desktop Nav
    [:<>
     [:nav {:class (conj nav-classes "-sm:hidden" "grid" "grid-flow-col" "grid-cols-2")}
      [:div {:class ["gap-2" "justify-start" "inline-flex"]}
       [nav-link "Home" :app.router/home]]
      [:div {:class ["gap-2" "justify-end" "inline-flex"]}
       [nav-link "Blog" :app.router/blog]
       [nav-link "About" :app.router/about]
       [dark-light-button toggle-button-classes]]]
     ;; Mobile Nav
     [:nav {:class (conj nav-classes "m-4" "sm:hidden" "fixed" "bottom-0" "rounded-lg" "right-0")}
      [:> headlessui/Popover
       [:> headlessui/Popover.Button
        {:class-name ["border-1" "bg-gray-200" "rounded"]}
        [:> FontAwesomeIcon {:icon fas/faEllipsisH}]]
       [:> headlessui/Popover.Panel
        [:div.grid.gap-2
         [nav-link "Home" :app.router/home]
         [nav-link "Blog" :app.router/blog]
         [nav-link "About" :app.router/about]
         [dark-light-button toggle-button-classes]]]]]]))

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
  [:div {:class ["border-2" "rounded" "border-gray-500"
                 "m-2" "pt-2" "pb-4" "px-4"
                 "bg-gray-200"]}
   [:a {:href (rfe/href :app.router/post {:id (-> blog-post :meta :id)})}
    [:h2 {:class ["text-2xl"]} (-> blog-post :meta :title)]
    [:p {:class ["text-xs py-0.5 text-gray-500"]}
     (display-date (-> blog-post :meta :date))]
    [:p {:class ["text-sm" "overflow-ellipsis" "line-clamp-5"
                 "text-gray-600"]}
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
      [:article {:class ["m-2" "mt-6" "pt-2"
                         "pb-4" "px-2" "sm:px-8"]}
       [:div
        [:h1 {:class ["font-bold text-4xl"]} (-> blog-post :meta :title)]
        [:p {:class ["pb-4" "text-gray-500"]}
         (display-date (-> blog-post :meta :date))]
        [:article {:class ["prose" "prose-sm" "sm:prose"
                           "lg:prose-lg" "mx-auto"]
                   :dangerouslySetInnerHTML
                   {:__html (:content blog-post)}}]]])}))

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
       [:> headlessui/Transition
        {:id "text-copy-indicator"
         :show @showing?
         :enter "transition-opacity duration-75"
         :enter-from "opacity-0"
         :enter-to "opacity-100"
         :leave "transition-opacity duration-300"
         :leave-from "opacity-100"
         :leave-to "opacity-0"
         :class ["border-2" "rounded-lg" "p-1" "absolute"
                 "text-black" "bg-blue-50"
                 "transform" "-translate-y-10"]}
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

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Pages
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn home-page
  []
  [:div {:class ["flex" "flex-col" "flex-grow"]}
   [:div {:class ["flex-grow" "flex" "justify-center" "items-center"]}
    [:h2 {:class ["text-5xl" "p-4" "italic"]}
     "Welcome," [:br {:class ["block" "sm:hidden"]}] " to my site!"]]])

(defn blog-preview-page
  []
  [:div {:class ["max-w-prose" "self-center" "pt-2"
                 "flex" "flex-col" "flex-initial" "items-stretch"]}
   (for [blog-post @blog-posts]
     ^{:key (-> blog-post :meta :id)}
     [blog-post-preview blog-post])])

(defn blog-post-page
  []
  (let [id   (->> @data/match :parameters :path :id)
        post (first (filter #(= id (-> % :meta :id)) @blog-posts))]
    [:div
     [blog-post-main-view post]
     [license]]))

(defn about-page
  []
  (let [about-header
        (fn [title subtitle]
          [:<>
           [:h1 {:class ["py-4" "text-3xl" "font-bold"]}
            title]
           [:h2 {:class ["italic" "pb-2"]}
            subtitle]])]
    [:div {:class ["max-w-prose" "self-center" "py-2" "px-8"
                   "flex" "flex-col" "flex-grow"
                   "items-center" "justify-between"]}
     [:div {:class ["min-h-screen"]}
      [about-header "Contact"
       "You seem pretty sweet. We should get lunch sometime."]
      [:ul
       [icon-link (:email data/global-config)
        fas/faEnvelope
        "Email address"
        :href (:email data/global-config)
        :mail true]
       [icon-link "wildwestrom"
        fab/faGithub
        "Github"
        :href (:github data/global-config)]
       [icon-link "c-westrom"
        fab/faLinkedin
        "Linkedin"
        :href (:linkedin data/global-config)]
       [icon-link (:discord data/global-config)
        fab/faDiscord
        "Discord"
        :copyable true]]
      [about-header "Funding"
       "Because every site needs a \"give me money\" button."]
      [:ul
       [icon-link "Monero Wallet"
        fab/faMonero
        "Monero wallet"
        :href (str "monero:" (:monero data/global-config))]]]
     [license]]))

(defn app
  []
  [:div {:class ["text-gray-700" "bg-gray-50"
                 "subpixel-antialiased" "min-h-screen"
                 "flex" "flex-col"]}
   [navbar]
   (if @data/match
     (let [view (:view (:data @data/match))]
       [view @data/match])
     (.log js/console
           (str "Match not found.\n `(:data @data/match)`:"
                (:data @data/match))))])
