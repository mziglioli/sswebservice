DELETE FROM `helparticle_category_category` where article_id <> 0;
DELETE FROM `helparticle_category` where category_id <> 0;
DELETE FROM `helparticles` where article_id <> 0;