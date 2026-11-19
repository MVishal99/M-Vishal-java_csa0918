# Task 2 – Hospital outpatient appointment desk

## Event model

| Source | Event | Listener | Action |
|---|---|---|---|
| Department combo box | ItemEvent | ItemListener | Reload doctors for selected department |
| Doctor combo box | ItemEvent | ItemListener | Refresh available slots |
| Book button | ActionEvent | ActionListener | Validate and book appointment |
| Window close | WindowEvent | WindowListener/WindowAdapter | Ask before closing during a booking |

## Component hierarchy

```text
JFrame
 └─ JPanel (BorderLayout)
    ├─ JPanel (GridLayout): patient ID, department, doctor, time slot
    ├─ JPanel: Book button
    └─ JLabel: status / confirmation
```

## Handler pseudocode

**Department changed:** Read selected department; get its doctor list from Map<String, List<Doctor>>; clear doctor combo; add matching doctors; clear slot selection.

**Book:** Validate patient ID, department, doctor and slot; show an error if any field is missing; mark bookingInProgress; call appointment service; show confirmation or error; clear bookingInProgress in a finally step.

**Close trace:** constructor → build components → register listeners → set visible → event queue dispatches events → windowClosing → ask for confirmation if bookingInProgress → dispose when permitted. Unlike an applet lifecycle, a Frame is created by its constructor and closed through window events; applet methods are init, start, paint, stop and destroy.
