(ns stuff (:require [clojure.core :refer :all]))

(defn fibs [] (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])))

(defn is-run-of [type] (fn [run] (every? #(= type %) run)))

(defn count-if [pred coll] (count (filter pred coll)))

(defn runs-of [length in-coll] (partition length 1 in-coll))

(defn pairs [in-coll] (runs-of 2 in-coll))

(defn count-runs-of [length type in-coll] (count-if (is-run-of type) (runs-of length in-coll)))

(def count-pairs-of (partial count-runs-of 2))
