# Q2 – User-defined exceptions and propagation
Expected output:
```
Saved answer01.pdf
Error: Could not store answer02.pdf
Cause: Disk quota exceeded
```
Storage wraps IOException in SubmissionStorageException and preserves the original cause. Compile and run StorageDemo.java.