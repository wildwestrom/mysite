(ns app.data.projects
  (:require [app.components.common :as common]
            ["@fortawesome/free-brands-svg-icons/faGithub" :refer [faGithub]]
            ["@fortawesome/free-brands-svg-icons/faNpm" :refer [faNpm]]
            ["@fortawesome/free-regular-svg-icons/faFileArchive" :refer [faFileArchive]]
            ["@fortawesome/free-solid-svg-icons/faRocket" :refer [faRocket]]))

(def portfolio
  [{:group-name "Finished"
    :projects   [{:name  "This Website"
                  :desc  ["This project started as an experiment in repl-driven-development, and is now the epicenter of my online presence. It's been really fun working with Reagent and learning more about how to use components from React."]
                  :links [{:title "Source"
                           :href  "https://github.com/wildwestrom/mysite"
                           :icon  faGithub}]}
                 {:name  "Uniorg Util"
                  :desc  ["This is a supplemental project meant to help out with the blog portion of my site. It converts " (common/generic-link "https://orgmode.org/" "Org documents") " to HTML and metadata for sorting and such. It's available as an NPM package. This is probably the best example of continuous integration and unit testing out of all my projects."]
                  :links [{:title "Source"
                           :href  "https://github.com/wildwestrom/uniorg-util"
                           :icon faGithub}
                          {:title "NPM Link"
                           :href  "https://www.npmjs.com/package/uniorg-util"
                           :icon faNpm}]}
                 {:name  "TSDL"
                  :desc  ["There's this artist who makes some cool desktop wallpapers, so I decided to make a web-scraping tool to help me download all the images on her site. The earliest iteration of it was written in POSIX-compliant shell-script. Now it's has a swing-based user interface (" [common/generic-link "https://github.com/clj-commons/seesaw" "seesaw"] "). This is also my first project where I use a logging framework (" [common/generic-link "https://github.com/BrunoBonacci/mulog" "mulog"] ") and make use of Clojure's " [:code "core.async"] "."]
                  :links [{:title "Source"
                           :href  "https://github.com/wildwestrom/tsdl"
                           :icon faGithub}
                          {:title "Release"
                           :href  "https://github.com/wildwestrom/tsdl/releases"
                           :icon faFileArchive}]}
                 {:name  "Hexagram SVG Generator"
                  :desc  ["The I-Ching is an ancient Chinese system of divination. It involves creating a figure of 6 lines, broken or unbroken, usually by casting coins or picking yarrow stalks. This demo generates an SVG graphic based on the hexagram number."]
                  :links [{:title "Source"
                           :href  "https://github.com/wildwestrom/hexagram-svg-generator"
                           :icon faGithub}
                          {:title "Demo"
                           :href  "https://hexagram-svg.netlify.app/"
                           :icon faRocket}]}]}
   {:group-name "In Progress"
    :projects   [{:name  "Death Calendar"
                  :desc  ["I was inspired by a " [common/generic-link "https://youtu.be/JXeJANDKwDc" "Kurzgesagt video"] " (which was also inspired by a blog-post on " [common/generic-link "https://waitbutwhy.com/2014/05/life-weeks.html" "waitbutwhy.com"] ") to make a calendar that shows how long you have left to live visually. I figured I could make it into a desktop background so I can use it as a constant reminder of my ultimate demise, and hopefully spend more time on the things that really matter."]
                  :links [{:title "Source"
                           :href  "https://github.com/wildwestrom/death-calendar"
                           :icon faGithub}]}]}])
