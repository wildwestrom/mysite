(ns app.core
  (:require [reagent.dom :as dom]
            [app.data :as data]))

(def root-classes '[bg-white dark:bg-black])

(def blog-post
  {:title "Lorem Ipsum"
   :body  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. A arcu cursus vitae congue mauris rhoncus aenean vel. Risus sed vulputate odio ut enim blandit. Leo duis ut diam quam nulla. Augue interdum velit euismod in pellentesque massa placerat. At imperdiet dui accumsan sit amet nulla facilisi morbi. Tincidunt tortor aliquam nulla facilisi. Consequat mauris nunc congue nisi vitae suscipit tellus mauris. A pellentesque sit amet porttitor eget. Urna molestie at elementum eu facilisis sed. Egestas erat imperdiet sed euismod nisi porta lorem. Aliquet bibendum enim facilisis gravida neque convallis a cras. Tellus rutrum tellus pellentesque eu. Malesuada pellentesque elit eget gravida cum. Volutpat sed cras ornare arcu dui vivamus arcu felis bibendum. Massa eget egestas purus viverra.
Pharetra et ultrices neque ornare aenean euismod elementum nisi quis. Leo a diam sollicitudin tempor id eu nisl nunc. Accumsan lacus vel facilisis volutpat est. Eros in cursus turpis massa tincidunt. Nibh tellus molestie nunc non blandit massa. In est ante in nibh. Turpis in eu mi bibendum neque egestas. Pulvinar neque laoreet suspendisse interdum consectetur. Nec feugiat nisl pretium fusce id velit ut. Bibendum ut tristique et egestas. Orci phasellus egestas tellus rutrum tellus.
Mattis molestie a iaculis at erat pellentesque adipiscing commodo. Neque viverra justo nec ultrices. Eget felis eget nunc lobortis mattis aliquam. Eu lobortis elementum nibh tellus molestie nunc non blandit massa. Nibh tellus molestie nunc non blandit massa enim. Consequat nisl vel pretium lectus quam id. Pellentesque habitant morbi tristique senectus et netus et malesuada fames. Pellentesque habitant morbi tristique senectus et netus. Varius quam quisque id diam vel quam elementum pulvinar. Vitae proin sagittis nisl rhoncus mattis rhoncus. Massa sed elementum tempus egestas sed sed risus pretium. Platea dictumst vestibulum rhoncus est.
Scelerisque felis imperdiet proin fermentum. Donec massa sapien faucibus et molestie ac. Velit sed ullamcorper morbi tincidunt ornare massa eget. Eget nullam non nisi est sit amet facilisis magna. Elementum curabitur vitae nunc sed velit dignissim sodales ut eu. Pellentesque elit eget gravida cum sociis natoque penatibus et. Justo eget magna fermentum iaculis eu non diam phasellus vestibulum. Id consectetur purus ut faucibus pulvinar elementum integer enim. Aliquet nibh praesent tristique magna sit amet. Amet aliquam id diam maecenas ultricies. Blandit aliquam etiam erat velit scelerisque in dictum non. Urna duis convallis convallis tellus id interdum velit laoreet id.
Leo vel fringilla est ullamcorper eget nulla. Elementum curabitur vitae nunc sed velit dignissim sodales ut eu. Adipiscing elit pellentesque habitant morbi tristique. Euismod elementum nisi quis eleifend. Euismod nisi porta lorem mollis aliquam ut porttitor leo a. Faucibus purus in massa tempor nec feugiat nisl. Eget mauris pharetra et ultrices neque ornare. Eget nullam non nisi est. Sagittis eu volutpat odio facilisis mauris sit. Risus ultricies tristique nulla aliquet enim tortor at auctor urna. Feugiat vivamus at augue eget arcu. Dis parturient montes nascetur ridiculus mus. Vitae tempus quam pellentesque nec nam aliquam sem. Amet facilisis magna etiam tempor orci eu lobortis elementum. Cursus mattis molestie a iaculis at erat. Et ultrices neque ornare aenean euismod elementum nisi."})

;; Views
(defn topbar [message title route]
  [:div {:class '[bg-black bg-opacity-50
                  p-2 text-lg font-serif
                  sticky top-0]}
   [:a {:href  route
        :title title}
    [:h1 {:class '[underline italic p-2]}
     message]]])

(defn blog-post-small []
  [:div {:class '[border-2 rounded border-gray-500
                  my-4 pt-2 pb-4 px-4 bg-gray-800]}
   [:a {:href (str "/" (:title blog-post))}
    [:h1 {:class '[text-2xl]} (:title blog-post)]]
   [:p {:class '[overflow-ellipsis line-clamp-5 text-gray-400]}
    (:body blog-post)]])

(def blog-posts-overview
  [:div {:id    "blog"
         :class '[min-h-screen]}
   (topbar "Here's some cool blog posts you can check out." "Blog" "/blog")
   (blog-post-small)])

(def content
  [:div {:id    "content"
         :class '[min-h-screen flex flex-col]}
   (topbar "West's Super Awesome Homepage!" "Home" "/")
   [:div {:class '[flex-grow items-center]}
    [:h2 {:class '[text-3xl p-4 italic]}
     "Welcome, to my site!"]]])

(def license
  [:footer {:class '[p-4 text-xs text-center self-center]}
   [:p "My "
    [:a {:href   "https://gitlab.com/wildwestrom/mysite"
         :target "_blank"
         :class  '[text-blue-500 visited:text-purple-500]}
     "static site generator"]
    " is licensed" [:br {:class '[xs:block hidden]}]  " under the "
    [:a {:href   "https://www.gnu.org/licenses/agpl-3.0.html"
         :target "_blank"
         :class  '[text-blue-500 visited:text-purple-500]}
     "GNU AGPL license"] "." [:br]
    "The rest is my own original work" [:br {:class '[xs:block hidden]}]
    " unless otherwise specified." [:br]
    "Copyright © " data/year " " data/author " " [:br {:class '[xs:block hidden]}]
    [:a {:href  (str "mailto: " data/email)
         :class '[text-blue-200 hover:text-blue-300]}
     data/email]]])

(def discord-icon
  [:svg {:id "Layer_1" :xmlns "http://www.w3.org/2000/svg" :viewBox "0 0 245 240"}
   [:path {:d "M104.4 103.9c-5.7 0-10.2 5-10.2 11.1s4.6 11.1 10.2 11.1c5.7 0 10.2-5 10.2-11.1.1-6.1-4.5-11.1-10.2-11.1zM140.9 103.9c-5.7 0-10.2 5-10.2 11.1s4.6 11.1 10.2 11.1c5.7 0 10.2-5 10.2-11.1s-4.5-11.1-10.2-11.1z"}]
   [:path {:d "M189.5 20h-134C44.2 20 35 29.2 35 40.6v135.2c0 11.4 9.2 20.6 20.5 20.6h113.4l-5.3-18.5 12.8 11.9 12.1 11.2 21.5 19V40.6c0-11.4-9.2-20.6-20.5-20.6zm-38.6 130.6s-3.6-4.3-6.6-8.1c13.1-3.7 18.1-11.9 18.1-11.9-4.1 2.7-8 4.6-11.5 5.9-5 2.1-9.8 3.5-14.5 4.3-9.6 1.8-18.4 1.3-25.9-.1-5.7-1.1-10.6-2.7-14.7-4.3-2.3-.9-4.8-2-7.3-3.4-.3-.2-.6-.3-.9-.5-.2-.1-.3-.2-.4-.3-1.8-1-2.8-1.7-2.8-1.7s4.8 8 17.5 11.8c-3 3.8-6.7 8.3-6.7 8.3-22.1-.7-30.5-15.2-30.5-15.2 0-32.2 14.4-58.3 14.4-58.3 14.4-10.8 28.1-10.5 28.1-10.5l1 1.2c-18 5.2-26.3 13.1-26.3 13.1s2.2-1.2 5.9-2.9c10.7-4.7 19.2-6 22.7-6.3.6-.1 1.1-.2 1.7-.2 6.1-.8 13-1 20.2-.2 9.5 1.1 19.7 3.9 30.1 9.6 0 0-7.9-7.5-24.9-12.7l1.4-1.6s13.7-.3 28.1 10.5c0 0 14.4 26.1 14.4 58.3 0 0-8.5 14.5-30.6 15.2z"}]
   ])

(def contact
  [:div {:class '[min-h-screen flex flex-col items-stretch]}
   (topbar '("You seem pretty sweet." [:br]
             "We should get lunch sometime.")
           "Contact" "/contact")
   [:div {:class '[flex-grow self-center flex flex-col]}
    [:ul {:class '[p-4 flex flex-col flex-grow justify-center]}
     [:li {:class '[py-2]} "Email: " data/email]
     [:li {:class '[py-2]} "Gitlab: " data/gitlab]
     [:li {:class '[py-2]} "Discord: " data/discord]]]
   license])

(defn app []
  [:div {:class '[dark:text-gray-300 dark:bg-gray-800]}
   content
   blog-posts-overview
   contact])

;; Render
(defn mount-root [c]
(dom/render [c]
            (.getElementById js/document "app")))

(defn ^:dev/after-load start []
(js/console.log "start")
(mount-root app))

(defn ^:export init []
(js/console.log "init")
(doseq [class root-classes]
  (.classList.add
    (.getElementById js/document "root") class))
(start))

(defn ^:dev/before-load stop []
  (js/console.log "stop"))
