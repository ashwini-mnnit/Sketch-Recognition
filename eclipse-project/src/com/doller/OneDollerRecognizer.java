package com.doller;

import java.util.ArrayList;
import java.util.List;

public class OneDollerRecognizer {
	private List<Template> templateList;
	
	OneDollerPoint[] pointTriangle = { new OneDollerPoint(137, 139), new OneDollerPoint(135, 141), new OneDollerPoint(133, 144), new OneDollerPoint(132, 146), new OneDollerPoint(130, 149), new OneDollerPoint(128, 151), new OneDollerPoint(126, 155), new OneDollerPoint(123, 160), new OneDollerPoint(120, 166), new OneDollerPoint(116, 171), new OneDollerPoint(112, 177), new OneDollerPoint(107, 183),
			new OneDollerPoint(102, 188), new OneDollerPoint(100, 191), new OneDollerPoint(95, 195), new OneDollerPoint(90, 199), new OneDollerPoint(86, 203), new OneDollerPoint(82, 206), new OneDollerPoint(80, 209), new OneDollerPoint(75, 213), new OneDollerPoint(73, 213), new OneDollerPoint(70, 216), new OneDollerPoint(67, 219), new OneDollerPoint(64, 221), new OneDollerPoint(61, 223), new OneDollerPoint(60, 225),
			new OneDollerPoint(62, 226), new OneDollerPoint(65, 225), new OneDollerPoint(67, 226), new OneDollerPoint(74, 226), new OneDollerPoint(77, 227), new OneDollerPoint(85, 229), new OneDollerPoint(91, 230), new OneDollerPoint(99, 231), new OneDollerPoint(108, 232), new OneDollerPoint(116, 233), new OneDollerPoint(125, 233), new OneDollerPoint(134, 234), new OneDollerPoint(145, 233), new OneDollerPoint(153, 232),
			new OneDollerPoint(160, 233), new OneDollerPoint(170, 234), new OneDollerPoint(177, 235), new OneDollerPoint(179, 236), new OneDollerPoint(186, 237), new OneDollerPoint(193, 238), new OneDollerPoint(198, 239), new OneDollerPoint(200, 237), new OneDollerPoint(202, 239), new OneDollerPoint(204, 238), new OneDollerPoint(206, 234), new OneDollerPoint(205, 230), new OneDollerPoint(202, 222),
			new OneDollerPoint(197, 216), new OneDollerPoint(192, 207), new OneDollerPoint(186, 198), new OneDollerPoint(179, 189), new OneDollerPoint(174, 183), new OneDollerPoint(170, 178), new OneDollerPoint(164, 171), new OneDollerPoint(161, 168), new OneDollerPoint(154, 160), new OneDollerPoint(148, 155), new OneDollerPoint(143, 150), new OneDollerPoint(138, 148), new OneDollerPoint(136, 148) };

	OneDollerPoint[] pointX = { new OneDollerPoint(87, 142), new OneDollerPoint(89, 145), new OneDollerPoint(91, 148), new OneDollerPoint(93, 151), new OneDollerPoint(96, 155), new OneDollerPoint(98, 157), new OneDollerPoint(100, 160), new OneDollerPoint(102, 162), new OneDollerPoint(106, 167), new OneDollerPoint(108, 169), new OneDollerPoint(110, 171), new OneDollerPoint(115, 177), new OneDollerPoint(119, 183),
			new OneDollerPoint(123, 189), new OneDollerPoint(127, 193), new OneDollerPoint(129, 196), new OneDollerPoint(133, 200), new OneDollerPoint(137, 206), new OneDollerPoint(140, 209), new OneDollerPoint(143, 212), new OneDollerPoint(146, 215), new OneDollerPoint(151, 220), new OneDollerPoint(153, 222), new OneDollerPoint(155, 223), new OneDollerPoint(157, 225), new OneDollerPoint(158, 223),
			new OneDollerPoint(157, 218), new OneDollerPoint(155, 211), new OneDollerPoint(154, 208), new OneDollerPoint(152, 200), new OneDollerPoint(150, 189), new OneDollerPoint(148, 179), new OneDollerPoint(147, 170), new OneDollerPoint(147, 158), new OneDollerPoint(147, 148), new OneDollerPoint(147, 141), new OneDollerPoint(147, 136), new OneDollerPoint(144, 135), new OneDollerPoint(142, 137),
			new OneDollerPoint(140, 139), new OneDollerPoint(135, 145), new OneDollerPoint(131, 152), new OneDollerPoint(124, 163), new OneDollerPoint(116, 177), new OneDollerPoint(108, 191), new OneDollerPoint(100, 206), new OneDollerPoint(94, 217), new OneDollerPoint(91, 222), new OneDollerPoint(89, 225), new OneDollerPoint(87, 226), new OneDollerPoint(87, 224) };

	// rectangle
	OneDollerPoint[] pointrectangle = { new OneDollerPoint(78, 149), new OneDollerPoint(78, 153), new OneDollerPoint(78, 157), new OneDollerPoint(78, 160), new OneDollerPoint(79, 162), new OneDollerPoint(79, 164), new OneDollerPoint(79, 167), new OneDollerPoint(79, 169), new OneDollerPoint(79, 173), new OneDollerPoint(79, 178), new OneDollerPoint(79, 183), new OneDollerPoint(80, 189), new OneDollerPoint(80, 193),
			new OneDollerPoint(80, 198), new OneDollerPoint(80, 202), new OneDollerPoint(81, 208), new OneDollerPoint(81, 210), new OneDollerPoint(81, 216), new OneDollerPoint(82, 222), new OneDollerPoint(82, 224), new OneDollerPoint(82, 227), new OneDollerPoint(83, 229), new OneDollerPoint(83, 231), new OneDollerPoint(85, 230), new OneDollerPoint(88, 232), new OneDollerPoint(90, 233), new OneDollerPoint(92, 232),
			new OneDollerPoint(94, 233), new OneDollerPoint(99, 232), new OneDollerPoint(102, 233), new OneDollerPoint(106, 233), new OneDollerPoint(109, 234), new OneDollerPoint(117, 235), new OneDollerPoint(123, 236), new OneDollerPoint(126, 236), new OneDollerPoint(135, 237), new OneDollerPoint(142, 238), new OneDollerPoint(145, 238), new OneDollerPoint(152, 238), new OneDollerPoint(154, 239),
			new OneDollerPoint(165, 238), new OneDollerPoint(174, 237), new OneDollerPoint(179, 236), new OneDollerPoint(186, 235), new OneDollerPoint(191, 235), new OneDollerPoint(195, 233), new OneDollerPoint(197, 233), new OneDollerPoint(200, 233), new OneDollerPoint(201, 235), new OneDollerPoint(201, 233), new OneDollerPoint(199, 231), new OneDollerPoint(198, 226), new OneDollerPoint(198, 220),
			new OneDollerPoint(196, 207), new OneDollerPoint(195, 195), new OneDollerPoint(195, 181), new OneDollerPoint(195, 173), new OneDollerPoint(195, 163), new OneDollerPoint(194, 155), new OneDollerPoint(192, 145), new OneDollerPoint(192, 143), new OneDollerPoint(192, 138), new OneDollerPoint(191, 135), new OneDollerPoint(191, 133), new OneDollerPoint(191, 130), new OneDollerPoint(190, 128),
			new OneDollerPoint(188, 129), new OneDollerPoint(186, 129), new OneDollerPoint(181, 132), new OneDollerPoint(173, 131), new OneDollerPoint(162, 131), new OneDollerPoint(151, 132), new OneDollerPoint(149, 132), new OneDollerPoint(138, 132), new OneDollerPoint(136, 132), new OneDollerPoint(122, 131), new OneDollerPoint(120, 131), new OneDollerPoint(109, 130), new OneDollerPoint(107, 130),
			new OneDollerPoint(90, 132), new OneDollerPoint(81, 133), new OneDollerPoint(76, 133) };

	// circle
	OneDollerPoint[] pointcircle = { new OneDollerPoint(127, 141), new OneDollerPoint(124, 140), new OneDollerPoint(120, 139), new OneDollerPoint(118, 139), new OneDollerPoint(116, 139), new OneDollerPoint(111, 140), new OneDollerPoint(109, 141), new OneDollerPoint(104, 144), new OneDollerPoint(100, 147), new OneDollerPoint(96, 152), new OneDollerPoint(93, 157), new OneDollerPoint(90, 163), new OneDollerPoint(87, 169),
			new OneDollerPoint(85, 175), new OneDollerPoint(83, 181), new OneDollerPoint(82, 190), new OneDollerPoint(82, 195), new OneDollerPoint(83, 200), new OneDollerPoint(84, 205), new OneDollerPoint(88, 213), new OneDollerPoint(91, 216), new OneDollerPoint(96, 219), new OneDollerPoint(103, 222), new OneDollerPoint(108, 224), new OneDollerPoint(111, 224), new OneDollerPoint(120, 224), new OneDollerPoint(133, 223),
			new OneDollerPoint(142, 222), new OneDollerPoint(152, 218), new OneDollerPoint(160, 214), new OneDollerPoint(167, 210), new OneDollerPoint(173, 204), new OneDollerPoint(178, 198), new OneDollerPoint(179, 196), new OneDollerPoint(182, 188), new OneDollerPoint(182, 177), new OneDollerPoint(178, 167), new OneDollerPoint(170, 150), new OneDollerPoint(163, 138), new OneDollerPoint(152, 130),
			new OneDollerPoint(143, 129), new OneDollerPoint(140, 131), new OneDollerPoint(129, 136), new OneDollerPoint(126, 139) };

	// check
	OneDollerPoint[] pointcheck = { new OneDollerPoint(91, 185), new OneDollerPoint(93, 185), new OneDollerPoint(95, 185), new OneDollerPoint(97, 185), new OneDollerPoint(100, 188), new OneDollerPoint(102, 189), new OneDollerPoint(104, 190), new OneDollerPoint(106, 193), new OneDollerPoint(108, 195), new OneDollerPoint(110, 198), new OneDollerPoint(112, 201), new OneDollerPoint(114, 204), new OneDollerPoint(115, 207),
			new OneDollerPoint(117, 210), new OneDollerPoint(118, 212), new OneDollerPoint(120, 214), new OneDollerPoint(121, 217), new OneDollerPoint(122, 219), new OneDollerPoint(123, 222), new OneDollerPoint(124, 224), new OneDollerPoint(126, 226), new OneDollerPoint(127, 229), new OneDollerPoint(129, 231), new OneDollerPoint(130, 233), new OneDollerPoint(129, 231), new OneDollerPoint(129, 228),
			new OneDollerPoint(129, 226), new OneDollerPoint(129, 224), new OneDollerPoint(129, 221), new OneDollerPoint(129, 218), new OneDollerPoint(129, 212), new OneDollerPoint(129, 208), new OneDollerPoint(130, 198), new OneDollerPoint(132, 189), new OneDollerPoint(134, 182), new OneDollerPoint(137, 173), new OneDollerPoint(143, 164), new OneDollerPoint(147, 157), new OneDollerPoint(151, 151),
			new OneDollerPoint(155, 144), new OneDollerPoint(161, 137), new OneDollerPoint(165, 131), new OneDollerPoint(171, 122), new OneDollerPoint(174, 118), new OneDollerPoint(176, 114), new OneDollerPoint(177, 112), new OneDollerPoint(177, 114), new OneDollerPoint(175, 116), new OneDollerPoint(173, 118) };

	// caret
	OneDollerPoint[] pointcaret = { new OneDollerPoint(79, 245), new OneDollerPoint(79, 242), new OneDollerPoint(79, 239), new OneDollerPoint(80, 237), new OneDollerPoint(80, 234), new OneDollerPoint(81, 232), new OneDollerPoint(82, 230), new OneDollerPoint(84, 224), new OneDollerPoint(86, 220), new OneDollerPoint(86, 218), new OneDollerPoint(87, 216), new OneDollerPoint(88, 213), new OneDollerPoint(90, 207),
			new OneDollerPoint(91, 202), new OneDollerPoint(92, 200), new OneDollerPoint(93, 194), new OneDollerPoint(94, 192), new OneDollerPoint(96, 189), new OneDollerPoint(97, 186), new OneDollerPoint(100, 179), new OneDollerPoint(102, 173), new OneDollerPoint(105, 165), new OneDollerPoint(107, 160), new OneDollerPoint(109, 158), new OneDollerPoint(112, 151), new OneDollerPoint(115, 144), new OneDollerPoint(117, 139),
			new OneDollerPoint(119, 136), new OneDollerPoint(119, 134), new OneDollerPoint(120, 132), new OneDollerPoint(121, 129), new OneDollerPoint(122, 127), new OneDollerPoint(124, 125), new OneDollerPoint(126, 124), new OneDollerPoint(129, 125), new OneDollerPoint(131, 127), new OneDollerPoint(132, 130), new OneDollerPoint(136, 139), new OneDollerPoint(141, 154), new OneDollerPoint(145, 166),
			new OneDollerPoint(151, 182), new OneDollerPoint(156, 193), new OneDollerPoint(157, 196), new OneDollerPoint(161, 209), new OneDollerPoint(162, 211), new OneDollerPoint(167, 223), new OneDollerPoint(169, 229), new OneDollerPoint(170, 231), new OneDollerPoint(173, 237), new OneDollerPoint(176, 242), new OneDollerPoint(177, 244), new OneDollerPoint(179, 250), new OneDollerPoint(181, 255),
			new OneDollerPoint(182, 257) };

	// question
	OneDollerPoint[] pointquestion = { new OneDollerPoint(104, 145), new OneDollerPoint(103, 142), new OneDollerPoint(103, 140), new OneDollerPoint(103, 138), new OneDollerPoint(103, 135), new OneDollerPoint(104, 133), new OneDollerPoint(105, 131), new OneDollerPoint(106, 128), new OneDollerPoint(107, 125), new OneDollerPoint(108, 123), new OneDollerPoint(111, 121), new OneDollerPoint(113, 118),
			new OneDollerPoint(115, 116), new OneDollerPoint(117, 116), new OneDollerPoint(119, 116), new OneDollerPoint(121, 115), new OneDollerPoint(124, 116), new OneDollerPoint(126, 115), new OneDollerPoint(128, 114), new OneDollerPoint(130, 115), new OneDollerPoint(133, 116), new OneDollerPoint(135, 117), new OneDollerPoint(140, 120), new OneDollerPoint(142, 121), new OneDollerPoint(144, 123),
			new OneDollerPoint(146, 125), new OneDollerPoint(149, 127), new OneDollerPoint(150, 129), new OneDollerPoint(152, 130), new OneDollerPoint(154, 132), new OneDollerPoint(156, 134), new OneDollerPoint(158, 137), new OneDollerPoint(159, 139), new OneDollerPoint(160, 141), new OneDollerPoint(160, 143), new OneDollerPoint(160, 146), new OneDollerPoint(160, 149), new OneDollerPoint(159, 153),
			new OneDollerPoint(158, 155), new OneDollerPoint(157, 157), new OneDollerPoint(155, 159), new OneDollerPoint(153, 161), new OneDollerPoint(151, 163), new OneDollerPoint(146, 167), new OneDollerPoint(142, 170), new OneDollerPoint(138, 172), new OneDollerPoint(134, 173), new OneDollerPoint(132, 175), new OneDollerPoint(127, 175), new OneDollerPoint(124, 175), new OneDollerPoint(122, 176),
			new OneDollerPoint(120, 178), new OneDollerPoint(119, 180), new OneDollerPoint(119, 183), new OneDollerPoint(119, 185), new OneDollerPoint(120, 190), new OneDollerPoint(121, 194), new OneDollerPoint(122, 200), new OneDollerPoint(123, 205), new OneDollerPoint(123, 211), new OneDollerPoint(124, 215), new OneDollerPoint(124, 223), new OneDollerPoint(124, 225) };

	// arrow
	OneDollerPoint[] pointarrow = { new OneDollerPoint(68, 222), new OneDollerPoint(70, 220), new OneDollerPoint(73, 218), new OneDollerPoint(75, 217), new OneDollerPoint(77, 215), new OneDollerPoint(80, 213), new OneDollerPoint(82, 212), new OneDollerPoint(84, 210), new OneDollerPoint(87, 209), new OneDollerPoint(89, 208), new OneDollerPoint(92, 206), new OneDollerPoint(95, 204), new OneDollerPoint(101, 201),
			new OneDollerPoint(106, 198), new OneDollerPoint(112, 194), new OneDollerPoint(118, 191), new OneDollerPoint(124, 187), new OneDollerPoint(127, 186), new OneDollerPoint(132, 183), new OneDollerPoint(138, 181), new OneDollerPoint(141, 180), new OneDollerPoint(146, 178), new OneDollerPoint(154, 173), new OneDollerPoint(159, 171), new OneDollerPoint(161, 170), new OneDollerPoint(166, 167),
			new OneDollerPoint(168, 167), new OneDollerPoint(171, 166), new OneDollerPoint(174, 164), new OneDollerPoint(177, 162), new OneDollerPoint(180, 160), new OneDollerPoint(182, 158), new OneDollerPoint(183, 156), new OneDollerPoint(181, 154), new OneDollerPoint(178, 153), new OneDollerPoint(171, 153), new OneDollerPoint(164, 153), new OneDollerPoint(160, 153), new OneDollerPoint(150, 154),
			new OneDollerPoint(147, 155), new OneDollerPoint(141, 157), new OneDollerPoint(137, 158), new OneDollerPoint(135, 158), new OneDollerPoint(137, 158), new OneDollerPoint(140, 157), new OneDollerPoint(143, 156), new OneDollerPoint(151, 154), new OneDollerPoint(160, 152), new OneDollerPoint(170, 149), new OneDollerPoint(179, 147), new OneDollerPoint(185, 145), new OneDollerPoint(192, 144),
			new OneDollerPoint(196, 144), new OneDollerPoint(198, 144), new OneDollerPoint(200, 144), new OneDollerPoint(201, 147), new OneDollerPoint(199, 149), new OneDollerPoint(194, 157), new OneDollerPoint(191, 160), new OneDollerPoint(186, 167), new OneDollerPoint(180, 176), new OneDollerPoint(177, 179), new OneDollerPoint(171, 187), new OneDollerPoint(169, 189), new OneDollerPoint(165, 194),
			new OneDollerPoint(164, 196) };

	// left square bracket
	OneDollerPoint[] pointLeftbracket = { new OneDollerPoint(140, 124), new OneDollerPoint(138, 123), new OneDollerPoint(135, 122), new OneDollerPoint(133, 123), new OneDollerPoint(130, 123), new OneDollerPoint(128, 124), new OneDollerPoint(125, 125), new OneDollerPoint(122, 124), new OneDollerPoint(120, 124), new OneDollerPoint(118, 124), new OneDollerPoint(116, 125), new OneDollerPoint(113, 125),
			new OneDollerPoint(111, 125), new OneDollerPoint(108, 124), new OneDollerPoint(106, 125), new OneDollerPoint(104, 125), new OneDollerPoint(102, 124), new OneDollerPoint(100, 123), new OneDollerPoint(98, 123), new OneDollerPoint(95, 124), new OneDollerPoint(93, 123), new OneDollerPoint(90, 124), new OneDollerPoint(88, 124), new OneDollerPoint(85, 125), new OneDollerPoint(83, 126), new OneDollerPoint(81, 127),
			new OneDollerPoint(81, 129), new OneDollerPoint(82, 131), new OneDollerPoint(82, 134), new OneDollerPoint(83, 138), new OneDollerPoint(84, 141), new OneDollerPoint(84, 144), new OneDollerPoint(85, 148), new OneDollerPoint(85, 151), new OneDollerPoint(86, 156), new OneDollerPoint(86, 160), new OneDollerPoint(86, 164), new OneDollerPoint(86, 168), new OneDollerPoint(87, 171), new OneDollerPoint(87, 175),
			new OneDollerPoint(87, 179), new OneDollerPoint(87, 182), new OneDollerPoint(87, 186), new OneDollerPoint(88, 188), new OneDollerPoint(88, 195), new OneDollerPoint(88, 198), new OneDollerPoint(88, 201), new OneDollerPoint(88, 207), new OneDollerPoint(89, 211), new OneDollerPoint(89, 213), new OneDollerPoint(89, 217), new OneDollerPoint(89, 222), new OneDollerPoint(88, 225), new OneDollerPoint(88, 229),
			new OneDollerPoint(88, 231), new OneDollerPoint(88, 233), new OneDollerPoint(88, 235), new OneDollerPoint(89, 237), new OneDollerPoint(89, 240), new OneDollerPoint(89, 242), new OneDollerPoint(91, 241), new OneDollerPoint(94, 241), new OneDollerPoint(96, 240), new OneDollerPoint(98, 239), new OneDollerPoint(105, 240), new OneDollerPoint(109, 240), new OneDollerPoint(113, 239), new OneDollerPoint(116, 240),
			new OneDollerPoint(121, 239), new OneDollerPoint(130, 240), new OneDollerPoint(136, 237), new OneDollerPoint(139, 237), new OneDollerPoint(144, 238), new OneDollerPoint(151, 237), new OneDollerPoint(157, 236), new OneDollerPoint(159, 237) };

	// right square bracket.
	OneDollerPoint[] pointRightbracket = { new OneDollerPoint(112, 138), new OneDollerPoint(112, 136), new OneDollerPoint(115, 136), new OneDollerPoint(118, 137), new OneDollerPoint(120, 136), new OneDollerPoint(123, 136), new OneDollerPoint(125, 136), new OneDollerPoint(128, 136), new OneDollerPoint(131, 136), new OneDollerPoint(134, 135), new OneDollerPoint(137, 135), new OneDollerPoint(140, 134),
			new OneDollerPoint(143, 133), new OneDollerPoint(145, 132), new OneDollerPoint(147, 132), new OneDollerPoint(149, 132), new OneDollerPoint(152, 132), new OneDollerPoint(153, 134), new OneDollerPoint(154, 137), new OneDollerPoint(155, 141), new OneDollerPoint(156, 144), new OneDollerPoint(157, 152), new OneDollerPoint(158, 161), new OneDollerPoint(160, 170), new OneDollerPoint(162, 182),
			new OneDollerPoint(164, 192), new OneDollerPoint(166, 200), new OneDollerPoint(167, 209), new OneDollerPoint(168, 214), new OneDollerPoint(168, 216), new OneDollerPoint(169, 221), new OneDollerPoint(169, 223), new OneDollerPoint(169, 228), new OneDollerPoint(169, 231), new OneDollerPoint(166, 233), new OneDollerPoint(164, 234), new OneDollerPoint(161, 235), new OneDollerPoint(155, 236),
			new OneDollerPoint(147, 235), new OneDollerPoint(140, 233), new OneDollerPoint(131, 233), new OneDollerPoint(124, 233), new OneDollerPoint(117, 235), new OneDollerPoint(114, 238), new OneDollerPoint(112, 238) };

	// v
	OneDollerPoint[] pointV = { new OneDollerPoint(89, 164), new OneDollerPoint(90, 162), new OneDollerPoint(92, 162), new OneDollerPoint(94, 164), new OneDollerPoint(95, 166), new OneDollerPoint(96, 169), new OneDollerPoint(97, 171), new OneDollerPoint(99, 175), new OneDollerPoint(101, 178), new OneDollerPoint(103, 182), new OneDollerPoint(106, 189), new OneDollerPoint(108, 194), new OneDollerPoint(111, 199),
			new OneDollerPoint(114, 204), new OneDollerPoint(117, 209), new OneDollerPoint(119, 214), new OneDollerPoint(122, 218), new OneDollerPoint(124, 222), new OneDollerPoint(126, 225), new OneDollerPoint(128, 228), new OneDollerPoint(130, 229), new OneDollerPoint(133, 233), new OneDollerPoint(134, 236), new OneDollerPoint(136, 239), new OneDollerPoint(138, 240), new OneDollerPoint(139, 242),
			new OneDollerPoint(140, 244), new OneDollerPoint(142, 242), new OneDollerPoint(142, 240), new OneDollerPoint(142, 237), new OneDollerPoint(143, 235), new OneDollerPoint(143, 233), new OneDollerPoint(145, 229), new OneDollerPoint(146, 226), new OneDollerPoint(148, 217), new OneDollerPoint(149, 208), new OneDollerPoint(149, 205), new OneDollerPoint(151, 196), new OneDollerPoint(151, 193),
			new OneDollerPoint(153, 182), new OneDollerPoint(155, 172), new OneDollerPoint(157, 165), new OneDollerPoint(159, 160), new OneDollerPoint(162, 155), new OneDollerPoint(164, 150), new OneDollerPoint(165, 148), new OneDollerPoint(166, 146) };

	// delete
	OneDollerPoint[] pointdelete = { new OneDollerPoint(123, 129), new OneDollerPoint(123, 131), new OneDollerPoint(124, 133), new OneDollerPoint(125, 136), new OneDollerPoint(127, 140), new OneDollerPoint(129, 142), new OneDollerPoint(133, 148), new OneDollerPoint(137, 154), new OneDollerPoint(143, 158), new OneDollerPoint(145, 161), new OneDollerPoint(148, 164), new OneDollerPoint(153, 170),
			new OneDollerPoint(158, 176), new OneDollerPoint(160, 178), new OneDollerPoint(164, 183), new OneDollerPoint(168, 188), new OneDollerPoint(171, 191), new OneDollerPoint(175, 196), new OneDollerPoint(178, 200), new OneDollerPoint(180, 202), new OneDollerPoint(181, 205), new OneDollerPoint(184, 208), new OneDollerPoint(186, 210), new OneDollerPoint(187, 213), new OneDollerPoint(188, 215),
			new OneDollerPoint(186, 212), new OneDollerPoint(183, 211), new OneDollerPoint(177, 208), new OneDollerPoint(169, 206), new OneDollerPoint(162, 205), new OneDollerPoint(154, 207), new OneDollerPoint(145, 209), new OneDollerPoint(137, 210), new OneDollerPoint(129, 214), new OneDollerPoint(122, 217), new OneDollerPoint(118, 218), new OneDollerPoint(111, 221), new OneDollerPoint(109, 222),
			new OneDollerPoint(110, 219), new OneDollerPoint(112, 217), new OneDollerPoint(118, 209), new OneDollerPoint(120, 207), new OneDollerPoint(128, 196), new OneDollerPoint(135, 187), new OneDollerPoint(138, 183), new OneDollerPoint(148, 167), new OneDollerPoint(157, 153), new OneDollerPoint(163, 145), new OneDollerPoint(165, 142), new OneDollerPoint(172, 133), new OneDollerPoint(177, 127),
			new OneDollerPoint(179, 127), new OneDollerPoint(180, 125) };

	// left curly brace
	OneDollerPoint[] pointleftCurlyBracket = { new OneDollerPoint(150, 116), new OneDollerPoint(147, 117), new OneDollerPoint(145, 116), new OneDollerPoint(142, 116), new OneDollerPoint(139, 117), new OneDollerPoint(136, 117), new OneDollerPoint(133, 118), new OneDollerPoint(129, 121), new OneDollerPoint(126, 122), new OneDollerPoint(123, 123), new OneDollerPoint(120, 125), new OneDollerPoint(118, 127), new OneDollerPoint(115, 128),
			new OneDollerPoint(113, 129), new OneDollerPoint(112, 131), new OneDollerPoint(113, 134), new OneDollerPoint(115, 134), new OneDollerPoint(117, 135), new OneDollerPoint(120, 135), new OneDollerPoint(123, 137), new OneDollerPoint(126, 138), new OneDollerPoint(129, 140), new OneDollerPoint(135, 143), new OneDollerPoint(137, 144), new OneDollerPoint(139, 147), new OneDollerPoint(141, 149),
			new OneDollerPoint(140, 152), new OneDollerPoint(139, 155), new OneDollerPoint(134, 159), new OneDollerPoint(131, 161), new OneDollerPoint(124, 166), new OneDollerPoint(121, 166), new OneDollerPoint(117, 166), new OneDollerPoint(114, 167), new OneDollerPoint(112, 166), new OneDollerPoint(114, 164), new OneDollerPoint(116, 163), new OneDollerPoint(118, 163), new OneDollerPoint(120, 162),
			new OneDollerPoint(122, 163), new OneDollerPoint(125, 164), new OneDollerPoint(127, 165), new OneDollerPoint(129, 166), new OneDollerPoint(130, 168), new OneDollerPoint(129, 171), new OneDollerPoint(127, 175), new OneDollerPoint(125, 179), new OneDollerPoint(123, 184), new OneDollerPoint(121, 190), new OneDollerPoint(120, 194), new OneDollerPoint(119, 199), new OneDollerPoint(120, 202),
			new OneDollerPoint(123, 207), new OneDollerPoint(127, 211), new OneDollerPoint(133, 215), new OneDollerPoint(142, 219), new OneDollerPoint(148, 220), new OneDollerPoint(151, 221) };

	// right curly brace
	OneDollerPoint[] pointrightCurlyBracket = { new OneDollerPoint(117, 132), new OneDollerPoint(115, 132), new OneDollerPoint(115, 129), new OneDollerPoint(117, 129), new OneDollerPoint(119, 128), new OneDollerPoint(122, 127), new OneDollerPoint(125, 127), new OneDollerPoint(127, 127), new OneDollerPoint(130, 127), new OneDollerPoint(133, 129), new OneDollerPoint(136, 129), new OneDollerPoint(138, 130),
			new OneDollerPoint(140, 131), new OneDollerPoint(143, 134), new OneDollerPoint(144, 136), new OneDollerPoint(145, 139), new OneDollerPoint(145, 142), new OneDollerPoint(145, 145), new OneDollerPoint(145, 147), new OneDollerPoint(145, 149), new OneDollerPoint(144, 152), new OneDollerPoint(142, 157), new OneDollerPoint(141, 160), new OneDollerPoint(139, 163), new OneDollerPoint(137, 166),
			new OneDollerPoint(135, 167), new OneDollerPoint(133, 169), new OneDollerPoint(131, 172), new OneDollerPoint(128, 173), new OneDollerPoint(126, 176), new OneDollerPoint(125, 178), new OneDollerPoint(125, 180), new OneDollerPoint(125, 182), new OneDollerPoint(126, 184), new OneDollerPoint(128, 187), new OneDollerPoint(130, 187), new OneDollerPoint(132, 188), new OneDollerPoint(135, 189),
			new OneDollerPoint(140, 189), new OneDollerPoint(145, 189), new OneDollerPoint(150, 187), new OneDollerPoint(155, 186), new OneDollerPoint(157, 185), new OneDollerPoint(159, 184), new OneDollerPoint(156, 185), new OneDollerPoint(154, 185), new OneDollerPoint(149, 185), new OneDollerPoint(145, 187), new OneDollerPoint(141, 188), new OneDollerPoint(136, 191), new OneDollerPoint(134, 191),
			new OneDollerPoint(131, 192), new OneDollerPoint(129, 193), new OneDollerPoint(129, 195), new OneDollerPoint(129, 197), new OneDollerPoint(131, 200), new OneDollerPoint(133, 202), new OneDollerPoint(136, 206), new OneDollerPoint(139, 211), new OneDollerPoint(142, 215), new OneDollerPoint(145, 220), new OneDollerPoint(147, 225), new OneDollerPoint(148, 231), new OneDollerPoint(147, 239),
			new OneDollerPoint(144, 244), new OneDollerPoint(139, 248), new OneDollerPoint(134, 250), new OneDollerPoint(126, 253), new OneDollerPoint(119, 253), new OneDollerPoint(115, 253) };

	// star
	OneDollerPoint[] pointStar = { new OneDollerPoint(75, 250), new OneDollerPoint(75, 247), new OneDollerPoint(77, 244), new OneDollerPoint(78, 242), new OneDollerPoint(79, 239), new OneDollerPoint(80, 237), new OneDollerPoint(82, 234), new OneDollerPoint(82, 232), new OneDollerPoint(84, 229), new OneDollerPoint(85, 225), new OneDollerPoint(87, 222), new OneDollerPoint(88, 219), new OneDollerPoint(89, 216),
			new OneDollerPoint(91, 212), new OneDollerPoint(92, 208), new OneDollerPoint(94, 204), new OneDollerPoint(95, 201), new OneDollerPoint(96, 196), new OneDollerPoint(97, 194), new OneDollerPoint(98, 191), new OneDollerPoint(100, 185), new OneDollerPoint(102, 178), new OneDollerPoint(104, 173), new OneDollerPoint(104, 171), new OneDollerPoint(105, 164), new OneDollerPoint(106, 158), new OneDollerPoint(107, 156),
			new OneDollerPoint(107, 152), new OneDollerPoint(108, 145), new OneDollerPoint(109, 141), new OneDollerPoint(110, 139), new OneDollerPoint(112, 133), new OneDollerPoint(113, 131), new OneDollerPoint(116, 127), new OneDollerPoint(117, 125), new OneDollerPoint(119, 122), new OneDollerPoint(121, 121), new OneDollerPoint(123, 120), new OneDollerPoint(125, 122), new OneDollerPoint(125, 125),
			new OneDollerPoint(127, 130), new OneDollerPoint(128, 133), new OneDollerPoint(131, 143), new OneDollerPoint(136, 153), new OneDollerPoint(140, 163), new OneDollerPoint(144, 172), new OneDollerPoint(145, 175), new OneDollerPoint(151, 189), new OneDollerPoint(156, 201), new OneDollerPoint(161, 213), new OneDollerPoint(166, 225), new OneDollerPoint(169, 233), new OneDollerPoint(171, 236),
			new OneDollerPoint(174, 243), new OneDollerPoint(177, 247), new OneDollerPoint(178, 249), new OneDollerPoint(179, 251), new OneDollerPoint(180, 253), new OneDollerPoint(180, 255), new OneDollerPoint(179, 257), new OneDollerPoint(177, 257), new OneDollerPoint(174, 255), new OneDollerPoint(169, 250), new OneDollerPoint(164, 247), new OneDollerPoint(160, 245), new OneDollerPoint(149, 238),
			new OneDollerPoint(138, 230), new OneDollerPoint(127, 221), new OneDollerPoint(124, 220), new OneDollerPoint(112, 212), new OneDollerPoint(110, 210), new OneDollerPoint(96, 201), new OneDollerPoint(84, 195), new OneDollerPoint(74, 190), new OneDollerPoint(64, 182), new OneDollerPoint(55, 175), new OneDollerPoint(51, 172), new OneDollerPoint(49, 170), new OneDollerPoint(51, 169), new OneDollerPoint(56, 169),
			new OneDollerPoint(66, 169), new OneDollerPoint(78, 168), new OneDollerPoint(92, 166), new OneDollerPoint(107, 164), new OneDollerPoint(123, 161), new OneDollerPoint(140, 162), new OneDollerPoint(156, 162), new OneDollerPoint(171, 160), new OneDollerPoint(173, 160), new OneDollerPoint(186, 160), new OneDollerPoint(195, 160), new OneDollerPoint(198, 161), new OneDollerPoint(203, 163),
			new OneDollerPoint(208, 163), new OneDollerPoint(206, 164), new OneDollerPoint(200, 167), new OneDollerPoint(187, 172), new OneDollerPoint(174, 179), new OneDollerPoint(172, 181), new OneDollerPoint(153, 192), new OneDollerPoint(137, 201), new OneDollerPoint(123, 211), new OneDollerPoint(112, 220), new OneDollerPoint(99, 229), new OneDollerPoint(90, 237), new OneDollerPoint(80, 244),
			new OneDollerPoint(73, 250), new OneDollerPoint(69, 254), new OneDollerPoint(69, 252) };

	// pig tail
	OneDollerPoint[] pointPigTail = { new OneDollerPoint(81, 219), new OneDollerPoint(84, 218), new OneDollerPoint(86, 220), new OneDollerPoint(88, 220), new OneDollerPoint(90, 220), new OneDollerPoint(92, 219), new OneDollerPoint(95, 220), new OneDollerPoint(97, 219), new OneDollerPoint(99, 220), new OneDollerPoint(102, 218), new OneDollerPoint(105, 217), new OneDollerPoint(107, 216), new OneDollerPoint(110, 216),
			new OneDollerPoint(113, 214), new OneDollerPoint(116, 212), new OneDollerPoint(118, 210), new OneDollerPoint(121, 208), new OneDollerPoint(124, 205), new OneDollerPoint(126, 202), new OneDollerPoint(129, 199), new OneDollerPoint(132, 196), new OneDollerPoint(136, 191), new OneDollerPoint(139, 187), new OneDollerPoint(142, 182), new OneDollerPoint(144, 179), new OneDollerPoint(146, 174),
			new OneDollerPoint(148, 170), new OneDollerPoint(149, 168), new OneDollerPoint(151, 162), new OneDollerPoint(152, 160), new OneDollerPoint(152, 157), new OneDollerPoint(152, 155), new OneDollerPoint(152, 151), new OneDollerPoint(152, 149), new OneDollerPoint(152, 146), new OneDollerPoint(149, 142), new OneDollerPoint(148, 139), new OneDollerPoint(145, 137), new OneDollerPoint(141, 135),
			new OneDollerPoint(139, 135), new OneDollerPoint(134, 136), new OneDollerPoint(130, 140), new OneDollerPoint(128, 142), new OneDollerPoint(126, 145), new OneDollerPoint(122, 150), new OneDollerPoint(119, 158), new OneDollerPoint(117, 163), new OneDollerPoint(115, 170), new OneDollerPoint(114, 175), new OneDollerPoint(117, 184), new OneDollerPoint(120, 190), new OneDollerPoint(125, 199),
			new OneDollerPoint(129, 203), new OneDollerPoint(133, 208), new OneDollerPoint(138, 213), new OneDollerPoint(145, 215), new OneDollerPoint(155, 218), new OneDollerPoint(164, 219), new OneDollerPoint(166, 219), new OneDollerPoint(177, 219), new OneDollerPoint(182, 218), new OneDollerPoint(192, 216), new OneDollerPoint(196, 213), new OneDollerPoint(199, 212), new OneDollerPoint(201, 211) };

	public OneDollerRecognizer() {
		super();
		this.templateList= new ArrayList<Template>();
		templateList.add(new Template("triangle", pointTriangle));
		templateList.add(new Template("x", pointX));
		templateList.add(new Template("ractangle", pointrectangle));
		templateList.add(new Template("circle", pointcircle));
		templateList.add(new Template("check", pointcheck));
		templateList.add(new Template("caret", pointcaret));
		templateList.add(new Template("question", pointquestion));
		templateList.add(new Template("arrow", pointarrow));
		templateList.add(new Template("Left Square", pointLeftbracket));
		templateList.add(new Template("Right Square", pointRightbracket));
		templateList.add(new Template("delete", pointdelete));
		templateList.add(new Template("Left Curly Bracket", pointleftCurlyBracket));
		templateList.add(new Template("Right Curly Bracket", pointrightCurlyBracket));
		templateList.add(new Template("Star", pointStar));
		templateList.add(new Template("Pig tail", pointPigTail));
	}
	
	
	private final int NumPoints = 64;
	private final float SquareSize = (float) 250.0;
	private final float AngleRange = (float) 45.0;
	private final float AnglePrecision = (float) 2.0;
	public OneDollerResult Recognize( OneDollerPoint [] points)
     {
		float HalfDiagonal = (float) (0.5 * Math.sqrt(250.0 * 250.0 + 250.0 * 250.0));
        points = OneDollerUtil.Resample( points, NumPoints);
        points = OneDollerUtil.RotateToZero( points );
        points = OneDollerUtil.ScaleToSquare(points, SquareSize);
        points = OneDollerUtil.TranslateToOrigin(points);
        float best = Float.MAX_VALUE;
        float sndBest = Float.MAX_VALUE;
        int t = -1;
        for( int i = 0; i < templateList.size(); i++)
        {
          float d = OneDollerUtil.DistanceAtBestAngle( points, templateList.get(i), -AngleRange, AngleRange, AnglePrecision);
          if( d < best )
          {
            sndBest = best;
            best = d;
            t = i;
          }
          else if( d < sndBest)
          {
            sndBest = d;
          }
        }
        float score = (float) (1.0 - (best / HalfDiagonal));
        float otherScore = (float) (1.0 - (sndBest / HalfDiagonal));
        float ratio = otherScore / score;
        // The threshold of 0.7 is arbitrary, and not part of the original code.
        if( t > -1 && score > 0.7)
        {
          return new OneDollerResult( templateList.get(t).Name, score, ratio );
        }
        else
        {
          return new OneDollerResult( "- none - ", (float)0.0, (float)1.0);
        }
     }
}
