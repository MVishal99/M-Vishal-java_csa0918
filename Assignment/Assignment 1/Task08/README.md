# Task 8 – Polymorphism
Compile and run `FeeDemo.java`. Expected output:
```
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true
```
The declared reference type is Vehicle, while the actual object selects the overridden hourly rate at runtime. calculateFee(int) is an example of a common polymorphic method; constructors are inherited through super calls.