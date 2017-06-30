.assembly extern mscorlib {}
.assembly sample01{}
.module sample01.exe
.class public _Principal{
.method static public void _principal()
{ .entrypoint
ldc.i8 1
call void [mscorlib]System.Console::Write(int64)
ldstr " "
call void [mscorlib]System.Console::Write(string)
ldc.r8 1.5
call void [mscorlib]System.Console::Write(float64)
ldstr "\n"
call void [mscorlib]System.Console::Write(string)
ldstr "olá mundo!"
call void [mscorlib]System.Console::Write(string)
ret
}
}
