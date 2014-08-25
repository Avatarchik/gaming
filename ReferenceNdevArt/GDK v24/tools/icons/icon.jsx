// Remember current unit settings and then set units to
// the value expected by this script
var originalUnit = app.preferences.rulerUnits
app.preferences.rulerUnits = Units.PIXELS;

// Create the options to save the png's with.
var pngSaveOptions = new PNGSaveOptions ();
pngSaveOptions.compression = 0;
pngSaveOptions.interlaced = false;

// Resize the image and save it for each size we need
resizeAndSave (57,true,true);
resizeAndSave (76);
resizeAndSave (120);
resizeAndSave (152);
resizeAndSave (72,true);
resizeAndSave (114,true);
resizeAndSave (144,true);

// Restore original ruler unit setting
app.preferences.rulerUnits = originalUnit;

// This function resizes the image, builds the file name, saves it out, and then steps back in the history to before the resize.
// This allows the script to perform multiple resizes without resizing a resized image (compounding image quality loss).
function resizeAndSave (newWidth,precomposed,excludeSize) {
	var doc = app.activeDocument;
	var suffixes = [];
	if (!excludeSize) suffixes.push(newWidth + "x" + newWidth);
    if (precomposed) suffixes.push("precomposed");
	var suffix = suffixes.join("-");
	resizeActivePictureAndScaleStyles(newWidth);
	doc.saveAs(new File(doc.path + "/apple-touch-icon-"+suffix+".png"),pngSaveOptions,true);
	doc.activeHistoryState = doc.historyStates[doc.historyStates.length - 2];
}

// Resize a picture WITH scaled styles
// app.activedocument.resizeImage() does NOT scale sizes
// Note: This function was pulled from a forum post online...I didn't write it.
function resizeActivePictureAndScaleStyles(newWidth)
{
	var idImgS = charIDToTypeID( "ImgS" );
	var desc2 = new ActionDescriptor();
	var idWdth = charIDToTypeID( "Wdth" );
	var idPxl = charIDToTypeID( "#Pxl" );
	desc2.putUnitDouble( idWdth, idPxl, newWidth);
	var idscaleStyles = stringIDToTypeID( "scaleStyles" );
	desc2.putBoolean( idscaleStyles, true );
	var idCnsP = charIDToTypeID( "CnsP" );
	desc2.putBoolean( idCnsP, true );
	var idIntr = charIDToTypeID( "Intr" );
	var idIntp = charIDToTypeID( "Intp" );
	var idBcbc = charIDToTypeID( "Bcbc" );
	desc2.putEnumerated( idIntr, idIntp, idBcbc );
	executeAction( idImgS, desc2, DialogModes.NO );
}