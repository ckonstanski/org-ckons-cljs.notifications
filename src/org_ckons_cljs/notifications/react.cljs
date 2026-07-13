(ns org-ckons-cljs.notifications.react
  (:require [reagent.core :as r]))

(declare reset-message)
(declare reset-errormsg)
(declare comp-message)
(declare comp-errormsg)

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
