.assembly extern mscorlib {}
.assembly sample04{}
.module sample04.exe
.class public _Principal{
.method static public void _principal()
{ .entrypoint
.locals(int64 CH)
label1:
ldstr "qual o CH de compiladores? "
call void [mscorlib]System.Console::Write(string)
call string [mscorlib]System.Console::ReadLine()
call int64 [mscorlib]System.Int64::Parse(string)
stloc CH
ldloc CH
ldc.i8 17
cgt
brfalse label1
ldstr "total de créditos: "
call void [mscorlib]System.Console::Write(string)
ldloc CH
ldc.i8 18
div
call void [mscorlib]System.Console::Write(int64)
ret
}
}
