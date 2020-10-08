(ns scify-clj.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [scify-clj.subs :as subs]
   [testdouble.cljs.csv :as csv]
   [ajax.core :refer [GET POST]]
   )
  (:require-macros [scify-clj.slurp :refer [slurp]]))


;; home
;; TODO require fastapi 3x rows in spreadsheet
(def data
  (csv/read-csv (slurp "/Users/markus/Projects/scify-cljs/resources/data/fibromyalgia02102020.csv"))
  )

(defn slider [param value min max invalidates]
  [:div
   [:input {:type "range" :value value :min min :max max
           :style {:width "100%"}
           :on-change ( #(print "slider") )}]])


(defn handler [response]
  (.log js/console (str response)))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "something bad happened: " status " " status-text)))


(defn fastapi-test [e]
  (.preventDefault e)
                (GET (str "http://127.0.0.1:8000/")
                           {:handler handler
                            :error-handler error-handler
                            }
                  ))

(defn buy-button []
  [:button
   {:on-click fastapi-test}
   "Buy"])


(defn diplay-item [item]
  [:div
   [:div.sentence
    (str (item 0))]
   [:div
    [:p [:a.article-link {:href (str (item 2))
                          :target "_blank"} (str (item 1))]]]
   [:div
    [:input.button {:type "buctton" :value "More like this ✅"
                    :on-click ( #(print "Positive") ) }]
    [:input.button {:type "button" :value "Fewer like this \uD83D\uDEAB"
                    :on-click ( #(print "Negative") ) }]
    [slider 1 1 5]]
   [:div.sentence
    (str (item 3))]
   [:div.border]
   [buy-button]
   ]

  )

(defn table []
  [:div
   (map (fn [row]
            (diplay-item (data row))
          )(range 1 5))
   ]
  )

(defn home-title []
  (let [name (re-frame/subscribe [::subs/name])]
    [re-com/title
     :label (str "Literature Research Tool \uD83C\uDF93\uD83E\uDDEC")
     :level :level1]))

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "About"
   :href "#/about"])

(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[home-title]
              [link-to-about-page]
              [table]
              ]])


;; about

(defn about-title []
  [re-com/title
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [re-com/hyperlink-href
   :label "Home"
   :href "#/"])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[about-title]
              [link-to-home-page]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :height "100%"
     :children [[panels @active-panel]]]))
