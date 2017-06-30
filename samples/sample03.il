.assembly extern mscorlib {}
.assembly sample03{}
.module sample03.exe
.class public _Principal{
.method static public void _principal()
{ .entrypoint
.locals(int64 lado)
.locals(int64 _area)
ldc.i8 0
stloc _area
ldstr "digite um valor para lado: "
call void [mscorlib]System.Console::Write(string)
call string [mscorlib]System.Console::ReadLine()
call int64 [mscorlib]System.Int64::Parse(string)
stloc lado
ldloc lado
ldc.i8 0
cgt
brfalse label1
ldloc lado
ldloc lado
mul
stloc _area
br label2
label1:
ldstr "valor inválido para lado - "
call void [mscorlib]System.Console::Write(string)
label2:
ldstr "área: "
call void [mscorlib]System.Console::Write(string)
ldloc _area
call void [mscorlib]System.Console::Write(int64)
ret
}
}
