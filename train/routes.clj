(ns train.routes (:require [clojure.core :refer :all]))
(import 'java.lang.Integer)

(def routes (sort-by :distance (for [route (re-seq #"[a-zA-Z]{2}\d" "AB5 BC4 CD8 DC8 DE6 AD5 CE2 EB3 AE7")] 
  (array-map :origin (nth route 0) :destination (nth route 1) :distance (Integer/parseInt (str (nth route 2)))))))

(defn route-pairs [route-str] (for [route (re-seq #"(?=([a-zA-Z]-[a-zA-Z]))" route-str)] (nth route 1)))

(defn route-map [route-str] (for [pair (route-pairs route-str)] (array-map :origin (nth pair 0) :destination (nth pair 2))))

(defn describes-route [route-descriptor route]
  (and (= (get route-descriptor :origin) (get route :origin))
       (= (get route-descriptor :destination) (get route :destination))))

(defn find-exact-route [route-str] (let [found-routes (for [route-descriptor (route-map route-str)]
  (reduce (fn [route1, route2] (or route1 (if (describes-route route-descriptor route2) route2))) nil routes))]
  (if (not-any? nil? found-routes) found-routes '())))

(defn routes-from [origin]
  (filter (fn [route] (= origin (get route :origin))) routes))

(defn destination-of [route-map] (:destination (last route-map)))

(defn connections-for [route-map]
  (map (fn [route] (concat route-map [route])) (routes-from (destination-of route-map))))

(defn routes-for [origin]
  (let [origins (map (fn [route] (seq [route])) (routes-from origin))
        routes (iterate (fn [routes] (mapcat (fn [route] (connections-for route)) routes)) origins)]
     (mapcat (fn [routes] routes) routes)))

(defn distance [route-map]
  (reduce (fn
    ([a b] (+ (or (:distance a) a) (:distance b))))
    0 route-map))

(defn stops [route-map]
  (reduce (fn [a b] (inc a)) 0 route-map))

(defn destination-filter [destination] (fn [route-map] (= destination (destination-of route-map))))

(defn max-distance-filter [max-distance] (fn [route-map] (<= (distance route-map) max-distance)))

(defn max-stops-filter [max-stops] (fn [route-map] (<= (stops route-map) max-stops)))

(defn filter-routes-for [origin filter-by] (take-while filter-by (routes-for origin)))

(defn show-route [route-map] 
  (let [
    no-route (str "NO SUCH ROUTE")
    route-str (reduce (fn
      ([a b] (if (= no-route a)
        (str (:origin b) (:destination b))
        (str a (:destination b)))))
      no-route route-map)]
    (if (= no-route route-str)
      no-route
      (str route-str (distance route-map)))))

