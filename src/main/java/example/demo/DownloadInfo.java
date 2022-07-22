//package example.demo;
//
//import cn.hutool.core.io.StreamProgress;
//import io.netty.handler.codec.http.HttpUtil;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
///**
// *
// * @author LR<br>
// * @date 2021/6/18 11:01
// */
//public class DownloadInfo {
//
//
//	public static void main(String[] args) throws IOException {
//		download("https://tongyongxiezuo.oss-cn-zhangjiakou.aliyuncs.com/desktop/Teambition/2.0.0/install/win32/ia32/Teambition-2.0.0-win.exe",
//				"D:\\Teambition-2.0.0-win.exe");
//	}
//
//
//	public static void download(String url, String fileSavePath) throws IOException {
//		HttpUtil.download(url, Files.newOutputStream(Paths.get(fileSavePath)),true, new DefaultStreamProgress());
//	}
//
//	static class DefaultStreamProgress implements StreamProgress {
//
//
//		private long size = 0;
//
//		private long time = System.currentTimeMillis();
//
//		@Override
//		public void start() {
//			System.out.println("start download");
//		}
//
//		@Override
//		public void progress(long progressSize) {
//			long block = progressSize - size;
//			// 1 MB
//			if (block >= (1 << 20)) {
//				long now = System.currentTimeMillis();
//				long blockTime = now - time;
//				System.out.printf("downloading... %d M --  %d B -- %d ms -- %d KB/s %n", (progressSize >> 20), block, blockTime, (block / blockTime * 1000) >> 10);
//				size = progressSize;
//				time = now;
//			}
//		}
//
//		@Override
//		public void finish() {
//			System.out.println("finish download");
//		}
//	}
//}
