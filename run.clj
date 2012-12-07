(use 'train.routes)

(println (str "Output #1: " (distance (find-exact-route "A-B-C"))))

(println (str "Output #2: " (distance (find-exact-route "A-D"))))

(println (str "Output #3: " (distance (find-exact-route "A-D-C"))))

(println (str "Output #4: " (distance (find-exact-route "A-E-B-C-D"))))

(println (str "Output #5: " (show-route (find-exact-route "A-E-D"))))

(println (str "Output #6: " (count (filter (destination-filter \C) (filter-routes-for \C (max-stops-filter 3))))))

(println (str "Output #7: " (count (filter (destination-filter \C) (filter-routes-for \A (exact-stops-filter 4))))))

(println (str "Output #8: " (distance (shortest-route \A \C))))

(println (str "Output #9: " (distance (shortest-route \B \B))))

(println (str "Output #10: " (count (filter (destination-filter \C) (filter-routes-for \C (distance-less-than-filter 30))))))
