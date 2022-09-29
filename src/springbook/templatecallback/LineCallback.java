package springbook.templatecallback;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
