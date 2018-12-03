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

  selectedFunction: string;

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
    this.selectedFunction = this.functionsStr[0];
    this.getOutputText();
  }

  onButtonClick() {
    //this.getOutputText();
    //this.selectedFunction = ???
    this.outputText.textTransformed += '.' + this.selectedFunction;
  }

  getOutputText(): void { 
    this.textService.getOutputText().subscribe(outputText => this.outputText = outputText);
  }

  // przykładowy request: http://localhost:8080/TextTransformer/transform?text=aaa&function=upper
}
