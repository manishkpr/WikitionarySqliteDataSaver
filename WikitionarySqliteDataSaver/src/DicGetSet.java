
public class DicGetSet {
  String word,type,mean;
  DicGetSet(String word,String type,String mean){
	  this.setMean(mean);
	  this.setType(type);
	  this.setWord(word);
  }
public String getWord() {
	return word;
}

public void setWord(String word) {
	this.word = word;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getMean() {
	return mean;
}

public void setMean(String mean) {
	this.mean = mean;
}
}
