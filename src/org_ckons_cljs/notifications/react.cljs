(ns org-ckons-cljs.notifications.react
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [hiccups.runtime :as hiccupsrt]
            [dommy.core :as dommy]
            [clojure.string :as str]
            [reagent.core :as r]
            [reagent.dom :as rd]
            [reagent.dom.client :as rdc]))

(declare reset-message)
(declare reset-errormsg)
(declare comp-message)
(declare comp-errormsg)
(declare set-message)
(declare set-errormsg)

(def notification-state (r/atom {:message nil :errormsg nil}))

(defn reset-message [message]
  (reset! notification-state (assoc @notification-state :message message)))

(defn reset-errormsg [errormsg]
  (reset! notification-state (assoc @notification-state :errormsg errormsg)))

(defn comp-message []
  [:div {:class "alert alert-success"
         :style {:display (cond (empty? (@notification-state :message)) "none" :else "block")}}
   (@notification-state :message)])

(defn comp-errormsg []
  [:div {:class "alert alert-danger"
         :style {:display (cond (empty? (@notification-state :errormsg)) "none" :else "block")}}
   (@notification-state :errormsg)])

(defn set-message [message]
  (reset! notification-state (assoc @notification-state :message message)))

(defn set-errormsg [errormsg]
  (reset! notification-state (assoc @notification-state :errormsg errormsg)))
