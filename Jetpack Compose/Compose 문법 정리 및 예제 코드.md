# Compose 기술 정리



### Box

**Box를 이용하여 한 요소를 다른 요소 위에 배치합니다.  자체 구성 가능한 함수를 작성하여 이러한 여러 레이아웃을 앱에 적합한 더욱 정교한 레이아웃으로 결합할 수 있습니다.**



**@예제 코드**

```kotlin
Box(
  modifier = Modifier
  		.background(color = Color.Red)
			.fillMaxWidth()
  		.height(200.dp)
){
  Text("Hello")
  Box(
  	modifier = Modifier
    		.fillMaxSize()
    		.padding(16.dp),
    contentAlignment = Aligment.BottomEnd,
  ) {
		Text("12345~!!!")
  }
}
```



### List, LazyColume

**리스트 형태의 레이아웃을 반복문 또는 items 함수를 사용하여 코드를 간단하고 간결하게 표현한 것으로 기존 XML Layout에서 사용하던 RecyclerView를 대체 할 수 있습니다.**



**@예제 코드**

```kotlin
LazyColumn(
   modifier = Modifier
      .background(color = Color.Green)
      .fillMaxWidth(),
   contentPadding = PaddingValues(16.dp),
   verticalArrangement = Arrangement.spacedBy(8.dp)
) {
   item {
     Text("Header")
   }
   items(50) { index ->
     Text("글씨$index")
   }
   item {
     Text("Footer")
   }
}
```



### Image, Card, State

**ImageCard를 사용해서 영화 포스터를 만들 수 있다. State상태 관리를 통해 포스터 하트표시를 구현한다.**



**@예제 코드**

```kotlin
var isFavorite by rememberSaveable {
  mutableStateOf(false)
}
ImageCard(
	modifier = Modifier
  	.fillMaxWidth(0.5f)
  	.padding(16.dp),
  isFavorite = isFavorite
) {
  isFavorite = favorite
}

@Composable
fun ImageCard (
	modifier: Modifier = Modifier,
  isFavorite: Boolean,
  onTabFavorite: (Boolean) -> Unit
) {
  Card(
  	modifier = Modifier,
    shape = RoundedCornerShape(8.dp),
    elevation = 5.dp
  ) {
    Box (
    	modifier  = Modifier.height(200.dp)
    )
  }
}
```



### Scaffold

**Scaffold를 이용하여 기본 머티리얼 디자인 레이아웃 구조로 UI를 구현할 수 있다.**



### TextField, Snackbar

TextField를 사용하여 값을 입력받고 SnackBar를 이용하여 하단 메세지바를 생성할 수 있다.



**@예제 코드**

```kotlin
val (text, setValue) = remember {
	mutableStateOf("")
	
	val scaffoldState = rememberScaffoldState()
	scope = rememberCoroutineScope()
	
	Scaffold (
		scaffoldState = scaffoldState
	) {
		Colume (
			modifier = Modifier.fillMaxSize()
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			TextField (
				value = text,
				onValueChange = setValue
			)
			
			Button(onClick = 
				scope.launch {
					scaffoldState.snackbarHostState.showSnackbar("Hello $Text")
				}
			}) {
				Text(text = "클릭")
			}
		}
	}
}
```



```kotlin
val navController = rememberNavController()
val scaffoldState = rememberScaffoldState()

NavHost(
	navController = navController,
	startDestination = "first"
){
	composable("first") {
		scaffoldState.snackbarHostState.showSnackbar("FirstScreen")
	}
	composable("second") {
		SecondScreen(navController)
	}
	composable("third/{value}") { backStackEntry ->
		ThirdScreen(
			value = backStackEntry.arguments?.getString("value") ?: "",
			navController)
	}
}
            
@Composable
fun SecondScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "second")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigateUp()
        }) {
            Text("back")
        }
    }
}

@Composable
fun ThirdScreen(value: String, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "third")
        Spacer(modifier = Modifier.height(16.dp))
        Text(value)
        Button(onClick = {
            navController.navigateUp()
        }) {
            Text("back")
        }
    }
}
```

