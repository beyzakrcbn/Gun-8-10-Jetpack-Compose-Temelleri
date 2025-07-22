 # Gün 8-10: Jetpack Compose Temelleri->


1-Proje oluşturuldu.

2-CounterScreen adlı @composable fonksiyonu eklendi ve içindeki ögeleri düzenleyebilmek için Column fonksiyona eklendi.

3-Sayaç durumunu yönetmek için remember ve mutableStateOf kullanıldı.

4-Buton işlevselliği onClick lambda ifadelerine eklendi ve Modifier.padding ile ögeler arasına boşluk eklendi.

5-Açık ve Kapalı mod eklendi.

6-MainActivity içindeki CounterScreen çağrısına isDarkThemeActive = useDarkTheme parametresi eklendi.

7-CounterScreendeki isDarkThemeActive parametresini kullanarak tema değiştirme butonu if (isDarkThemeActive) "Light Mode" else "Dark Mode" şeklinde ayarlandı.


 # Notlarım->
------------------------

# Jetpack Compose Nedir?
Jetpack Compose, Google tarafından geliştirilmiş modern Android UI toolkit'idir. Declarative (bildirimsel) programlama yaklaşımı kullanarak UI geliştirme sürecini basitleştirir.

### Composable Fonksiyon Kuralları
1. **İsimlendirme**: PascalCase kullanın (büyük harfle başlayan)
2. **Side Effects**: Yan etkilerden kaçının
3. **Immutability**: Immutable parametreler kullanın
4. **Recomposition**: Fonksiyon birden fazla çağrılabilir

## Temel UI Bileşenleri
Text Bileşeni
Button Bileşeni
Column(Dikey Sıralama)
Row(Yatay Sıralama)

## State Managment(Durum Yönetimi)
`remember`, değerleri composition boyunca saklar ve recomposition'da korunmasını sağlar.
`mutableStateOf`, Compose'un state değişikliklerini takip etmesini sağlar.
`State Housting` (Durum Kaldırma) State'i üst component'a taşıyarak yeniden kullanılabilir component'lar oluşturur.

## Modifier Kullanımı
Modifier'lar, Composable'ların görünümünü, davranışını ve layout'unu değiştirmek için kullanılır.

# Temel Modifier'lar

@Composable
  fun ModifierExamples() {
    Column {
        // Boyut modifier'ları
        Text(
            "Sabit boyut",
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .background(Color.LightGray)
        )
        
        // Doldurma modifier'ları
        Text(
            "Fill modifiers",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightBlue)
        )
        
        // Padding ve margin
        Text(
            "Padding örneği",
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Yellow)
                .padding(8.dp) // İç padding
        )
        
        // Tıklanabilir
        Text(
            "Tıklanabilir metin",
            modifier = Modifier
                .clickable { /* Tıklama eventi */ }
                .background(Color.Green)
                .padding(12.dp)
        )
        
        // Şekil ve kenarlık
        Text(
            "Şekillendirilmiş",
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(2.dp, Color.Red, RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(16.dp)
        )
    }
}

# Zincir Halinde Modifier Kullanımı  !!!!

@Composable
fun ChainedModifiers() {
Text(
"Zincir halinde modifier",
modifier = Modifier
.fillMaxWidth() // 1. Genişliği doldur
.padding(16.dp) // 2. Dış padding ekle
.background( // 3. Arka plan rengi
Color.Blue,
RoundedCornerShape(8.dp)
)
.padding(12.dp) // 4. İç padding
.clickable { /* Tıklama */ } // 5. Tıklanabilir yap
)
}

# Theming ve Material Design 3  !!!!!

## Material Theme Kurulumu

@Composable
fun MyApp() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFF03DAC6),
            background = Color(0xFFF5F5F5),
            surface = Color.White,
            onPrimary = Color.White,
            onSecondary = Color.Black
        ),
        typography = Typography(
            headlineLarge = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            ),
            bodyLarge = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        ),
        shapes = Shapes(
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(8.dp),
            large = RoundedCornerShape(16.dp)
        )
    ) {
        MainScreen()
    }
}


## Dark Theme Desteği

@Composable
fun MyAppWithDarkTheme() {
    val darkTheme = isSystemInDarkTheme()
    
    MaterialTheme(
        colorScheme = if (darkTheme) {
            darkColorScheme(
                primary = Color(0xFFBB86FC),
                secondary = Color(0xFF03DAC6),
                background = Color(0xFF121212),
                surface = Color(0xFF1E1E1E)
            )
        } else {
            lightColorScheme(
                primary = Color(0xFF6200EE),
                secondary = Color(0xFF03DAC6),
                background = Color.White,
                surface = Color(0xFFF5F5F5)
            )
        }
    ) {
        MainScreen()
    }
}


## Theme Değerlerini Kullanma

@Composable
fun ThemedComponents() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            "Ana Başlık",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                "Kart içeriği",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}