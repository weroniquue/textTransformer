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

  functions: string[] = [
    'upper',
    'lower',
    'capitalize',
    'reverse',
    'num2String',
    'abbreviate',
    'encode',
    'decode',
    'encodeBinary',
    'decodeBinary',
    'removeDuplicates'
  ]

  constructor(private textService: TextService) { }

  ngOnInit() {
    this.inputText = "example input";
    this.selectedFunction = "reverse";
  }

  reuseOutput() {
    this.inputText = this.outputText.textTransformed;
  }

  transform(chosenFunction: string, inputText: string) {
    console.log(chosenFunction, inputText);
    this.requestPath = 'text=' + inputText + '&function=' + chosenFunction;
    console.log(this.requestPath)
    this.textService.getOutputText(this.requestPath).subscribe(outputText => this.outputText = outputText);
  }

}
