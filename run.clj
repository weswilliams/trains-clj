(use 'train.routes)

(println (str "Output #1: " (distance (find-exact-route "A-B-C"))))

(println (str "Output #2: " (distance (find-exact-route "A-D"))))

(println (str "Output #3: " (distance (find-exact-route "A-D-C"))))

(println (str "Output #4: " (distance (find-exact-route "A-E-B-C-D"))))

(println (str "Output #5: " (show-route (find-exact-route "A-E-D"))))

(println (str "Output #6: " (count (filter (destination-filter \C) (filter-routes-for \C (max-stops-filter 3))))))
