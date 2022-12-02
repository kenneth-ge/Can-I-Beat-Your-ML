# Hello and welcome to this week's game show of "Can I Beat Your ML algorithm?"
Today, we are trying to see whether we can classify an image as a desert or a forest with accuracy greater than or equal to that of an ML model without using ML or data. 

Our ML contender for today has an accuracy of 100% for the desert images, and 95% for forests. 

And our non-ML contender here is using a classic technique from FTC Team 11205 Edgemonsters, where it will pixelate the image into buckets and use HSV to determine whether there is more green or yellow present. 

So far, the accuracy of each model looks like this:
|                  | ML Model | Non-ML Algorithm |
|------------------|----------|------------------|
| % Correct Desert | 95%      | 99.50%           |
| % Correct Forest | 100%     | 95.51%           |
| % Correct Total  | 97.50%   | 97.51%           |

As you can see, the models are neck and neck. But will further development allow one of them win? What about overfitting? Speed/performance? Only time will tell...
