# SIR_Project
## Context
Project realised for a Programing Oriented Object (POO) course in Polytech Lyon (France).

|**Made by**|**Referent teacher**|
|:-:|:-:|
|SCART Gaëtan|Mr. LEFORT Mathieu|
|OULED MOUSSA Yanis|

**Grade** : 19.75/20

## Project
### Introduction

This application has been made using the JavaFx librairie : https://openjfx.io/

This project aims to simulate the dynamics of propagation of an epidemic in a population.
Different models are studied to show the differnet parameter's influence on the simulation.

This application offers two different ways to show the propagation :
- A graph, assuming that every person is able to infect every other person 
- A spatialization view, where everyone is showed by a colored circle showing their health state

This project has been inspired by this web page made by a student in mathematics and a researcher engineer working for the CNRS :

[CNRS Link](https://images.math.cnrs.fr/Modelisation-d-une-epidemie-partie-1.html)

### Models
#### SIR Model

SIR Model is the simplest model to describe epidemics. It has been proposed in 1927 in relation with the vaccination health policy against smallpox.

**Symbols**
- **β** : Contamination rate
- **γ** : Retire rate

This model sepaates population in three categories :
- **S** : Healthy people
- **I** : Infected people
- **R** : Retired people (Recovered or dead)

![image](https://user-images.githubusercontent.com/27727784/125475181-b6432977-d689-4502-bde4-6d86809461d8.png)

Equations defining this model :

![\[\LARGE \begin{cases} \frac{dS(t)}{dt} & = -\beta S(t) I(t)\\ \frac{dI(t)}{dt} & = \beta S(t) I(t) - \gamma I(t)\\ \frac{dR(t)}{dt} & = \gamma I(t) \end{cases}\]
](https://latex.codecogs.com/png.latex?%5Cbg_white%20%5CLARGE%20%5Cbegin%7Bcases%7D%20%5Cfrac%7BdS%28t%29%7D%7Bdt%7D%20%26%20%3D%20-%5Cbeta%20S%28t%29%20I%28t%29%5C%5C%20%5Cfrac%7BdI%28t%29%7D%7Bdt%7D%20%26%20%3D%20%5Cbeta%20S%28t%29%20I%28t%29%20-%20%5Cgamma%20I%28t%29%5C%5C%20%5Cfrac%7BdR%28t%29%7D%7Bdt%7D%20%26%20%3D%20%5Cgamma%20I%28t%29%20%5Cend%7Bcases%7D, "\[\LARGE \begin{cases} \frac{dS(t)}{dt} & = -\beta S(t) I(t)\\ \frac{dI(t)}{dt} & = \beta S(t) I(t) - \gamma I(t)\\ \frac{dR(t)}{dt} & = \gamma I(t) \end{cases}\]
") 

#### SEIR Model

SEIR model is a little more complex model aiming to be more accurate by simulating an incubation period. 
To do so, a new category **E (Exposed)** has been added to the model. This category is located between healthy and infected people.

**Symbols**
- **α** : Infectiousness

![image](https://user-images.githubusercontent.com/27727784/125476079-d6f259a3-e658-49d0-ae48-882c4849f514.png)

The new equations :

![image](https://user-images.githubusercontent.com/27727784/125476179-d66924ed-4ec8-4f73-b287-ca52cbaade36.png)

#### SEIR Born Model

This model is similar to SEIR model unlike the population is not constant in this one.
This model allows longer term studies than the others as the population acts more accurately with borns and deaths of the population.

**Symbols**
- **η** : Born rate
- **µ** : Death rate

![image](https://user-images.githubusercontent.com/27727784/125476315-ea545638-0c69-437a-97dd-b6309a5d96d8.png)

New equations :

![image](https://user-images.githubusercontent.com/27727784/125476374-fa66abc7-0d31-41b3-9bd1-8a64a5ad7af0.png)

