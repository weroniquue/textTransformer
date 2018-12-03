import { Component, OnInit } from '@angular/core';
import { TextService } from '../text.service';
import { TextTransformed } from '../models/text-transformed';

@Component({
  selector: 'app-textfields',
  templateUrl: './textfields.component.html',
  styleUrls: ['./textfields.component.css']
})
export class TextfieldsComponent implements OnInit {

  inputText: string;
  outputText: TextTransformed;
  requestPath: string;
  selectedFunction: string;
  selectedFunctionModel: string;

  functionsStr: string[] = [
    'upper',
    'lower',
    'capitalize',
    'reverse',
    'num2StringTransform',
    'abbreviate',
    'encode',
    'decode'
  ]

  constructor(private textService: TextService) { }

  ngOnInit() {
    this.selectedFunctionModel = "upper";
  }

  onButtonClick(chosenFunction: string) {
    this.selectedFunction = chosenFunction;
    //Tu przekazujesz tez input dla przykladu
    this.getOutputText(this.selectedFunction, "aaaaa");
    //this.outputText.textTransformed += '.' + this.selectedFunction;

  }

  getOutputText(selecectedFunction:string, inputText: string): void {
    console.log(this.selectedFunction, inputText);
    this.requestPath = 'text=' + inputText+'&function=' + this.selectedFunction;
    console.log(this.requestPath)
    this.textService.getOutputText(this.requestPath).subscribe(outputText => this.outputText = outputText);
  }

  // przykładowy request: http://localhost:8080/TextTransformer/transform?text=aaa&function=upper
}
