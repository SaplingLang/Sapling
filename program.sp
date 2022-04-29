fn Goodbye() {
    print "Goodbye, " + @module + "::" + @func;
}

fn Hello() {
    print "Hello, " + @module + "::" + @func;
    Goodbye();
}

Hello();

